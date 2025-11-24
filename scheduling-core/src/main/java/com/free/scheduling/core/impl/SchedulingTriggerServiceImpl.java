package com.free.scheduling.core.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.free.schedling.common.exception.JobException;
import com.free.schedling.common.redis.RedisUtil;
import com.free.scheduling.api.base.JobDetailRpcDTO;
import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.base.SchedulingUpdateRpcRequest;
import com.free.scheduling.api.dto.RegisterJobDetailRpcRequest;
import com.free.scheduling.api.dto.RegisterJobRpcRequest;
import com.free.scheduling.biz.service.SchedulingJobDetailReadService;
import com.free.scheduling.biz.service.SchedulingJobDetailUpdateService;
import com.free.scheduling.biz.service.SchedulingJobUpdateService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.*;
import com.free.scheduling.core.SchedulingTriggerService;
import com.free.scheduling.core.enums.JobStatusEnum;
import com.free.scheduling.core.exception.TriggerException;
import com.free.scheduling.core.generic.SchedulingInvokeService;
import com.free.scheduling.dao.entity.JobDetail;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author funanbing
 * @date 2025-10-09 17:18:55
 * @description
 */
@Service
@RequiredArgsConstructor
public class SchedulingTriggerServiceImpl implements SchedulingTriggerService {


    private final LinkedBlockingQueue<JobDetailDTO> execJobDetailDTOQueue = new LinkedBlockingQueue<>();

    private final  ThreadPoolExecutor TriggerPool = new ThreadPoolExecutor(
            10,
            200,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            r -> new Thread(r, "JobTriggerPool-" + r.hashCode()));
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private final static Long INTERVAL_TIME = 3000L;
    private final static String JOB_CACHE_KEY = "scheduling:job:cache";
    private final static String JOB_DETAIL_CACHE_KEY = "scheduling:job:detail:cache:";
    private final static String JOB_DETAIL_LOCK_KEY= "scheduling:job:detail:lock";
    private final static AtomicBoolean isStart = new AtomicBoolean(false);

    private final SchedulingInvokeService schedulingInvokeService;
    private final SchedulingJobUpdateService schedulingJobUpdateService;
    private final SchedulingJobDetailUpdateService schedulingJobDetailUpdateService;
    private final SchedulingJobDetailReadService schedulingJobDetailReadService;

    @PostConstruct
    public void init() {
        runExecJobDetail();
    }

    @SneakyThrows
    @Override
    public boolean registerJob(RegisterJobRpcRequest request) {
        if (Objects.isNull(request)) {
            throw new TriggerException("trigger job is null");
        }
        CreateJobParam param = CreateJobParam.builder().jobName(request.getJobName()).jobCode(request.getJobCode()).
                jobType(request.getJobType()).remark(request.getRemark()).status(request.getStatus()).build();
        Long jobInfoId = schedulingJobUpdateService.registerJob(param);
        String strId = String.valueOf(jobInfoId);
        if (Objects.isNull(RedisUtil.getMapValue(JOB_CACHE_KEY, strId))) {
            RedisUtil.setMap(JOB_CACHE_KEY, Map.of(strId, JobInfoDTO.builder()
                    .jobInfoId(jobInfoId)
                    .jobName(request.getJobName())
                    .jobCode(request.getJobCode())
                    .jobType(request.getJobType())
                    .status(request.getStatus())
                    .jobCallBack(JobCallBackDTO.builder()
                            .interfaceName(request.getCallback().getInterfaceName())
                            .paramTypes(request.getCallback().getParamTypes())
                            .method(request.getCallback().getMethod())
                            .args(request.getCallback().getArgs())
                            .extParam(request.getCallback().getExtParam())
                            .build())
                    .build()));
        }
        return true;
    }

    @SneakyThrows
    @Override
    public boolean registerJobDetail(RegisterJobDetailRpcRequest request) {
        if (Objects.isNull(request)) {
            throw new TriggerException("job detail is null");
        }
        CreateJobDetailParam detailParam = CreateJobDetailParam.builder().jobDetailCode(request.getJobDetailCode())
                .jobDetailName(request.getJobDetailName()).jobInfoId(String.valueOf(request.getJobInfoId()))
                .remark(request.getRemark()).executeTime(request.getExecTime()).build();
        Long jobDetailId = schedulingJobDetailUpdateService.registerJobDetail(detailParam);

        String jobInfoIdStr = String.valueOf(request.getJobInfoId());
        if (Objects.nonNull(RedisUtil.getMapValue(JOB_CACHE_KEY, jobInfoIdStr))) {
            execJobDetailDTOQueue.add(createJobDetailDTO(request.getJobInfoId(), jobDetailId, request.getExecTime()));
        }
        return true;
    }

    @Override
    public boolean update(SchedulingUpdateRpcRequest request) {
        try {
            RedisUtil.delMapValue(JOB_CACHE_KEY, request.getJobInfoId().toString());
            ResponseEntity<Boolean> response = schedulingJobUpdateService.modifyJob(ModifyJobParam.builder()
                    .id(String.valueOf(request.getJobInfoId())).status(request.getStatus()).build());
            if (!response.isSuccess()) {
                throw new JobException(response.getMsg());
            }
            if ((JobStatusEnum.DELETE.getCode().equals(request.getStatus())
                    || JobStatusEnum.STOP.getCode().equals(request.getStatus()))
                    && !CollectionUtils.isEmpty(execJobDetailDTOQueue)) {
                for (JobDetailDTO jobDetailDTO : execJobDetailDTOQueue) {
                    try {
                        if (RedisUtil.lock(JOB_DETAIL_CACHE_KEY + jobDetailDTO.getJobInfoId())) {
                            if (jobDetailDTO.getJobInfoId().equals(jobDetailDTO.getJobInfoId())) {
                                execJobDetailDTOQueue.remove(jobDetailDTO);
                            }
                        }
                    } catch (Exception e) {
                        throw new JobException(e.getMessage());
                    } finally {
                        RedisUtil.unlock(JOB_DETAIL_CACHE_KEY + jobDetailDTO.getJobInfoId());
                    }
                }
            }
        } catch (Exception e) {
            throw new JobException(e.getMessage());
        }
        return false;
    }

    private void runExecJobDetail() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (!isStart.get()) {
                loadJobDetailExec();
               isStart.set(true);
            }
            while (!CollectionUtils.isEmpty(execJobDetailDTOQueue)) {
                JobDetailDTO jobDetailDTO = execJobDetailDTOQueue.poll();
                if (Objects.isNull(jobDetailDTO)) {
                    continue;
                }
                JobInfoDTO jobInfoDTO = (JobInfoDTO)RedisUtil.getMapValue(JOB_CACHE_KEY, String.valueOf(jobDetailDTO.getJobInfoId()));
                if (Objects.nonNull(jobInfoDTO) && JobStatusEnum.RUNNING.getCode().equals(jobInfoDTO.getStatus())) {
                    long now = System.currentTimeMillis()/1000;
                    if (now >= jobDetailDTO.getExecTime() || now >= jobDetailDTO.getExecTime()+ INTERVAL_TIME) {
                        TriggerPool.execute(() -> {
                            CompletableFuture<Object> future = schedulingInvokeService.invoke(jobInfoDTO.getJobCallBack().getInterfaceName(),
                                    jobInfoDTO.getJobCallBack().getMethod(),
                                    jobInfoDTO.getJobCallBack().getParamTypes(),
                                    jobInfoDTO.getJobCallBack().getArgs());
                            Object object = null;
                            try {
                                object = future.get(2, TimeUnit.SECONDS);
                                RpcResponse<JobDetailRpcDTO> response = JSONObject.parseObject(JSONObject.toJSONString(object), new TypeReference<>() {});
                                if (response.getCode() == 200 && response.getData().isNext()) {
                                    execJobDetailDTOQueue.add(createJobDetailDTO(
                                            jobDetailDTO.getJobInfoId(),
                                            jobDetailDTO.getJobDetailId(),
                                            response.getData().getExecTime()));
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            }
        }, 0,2, TimeUnit.SECONDS);
    }

    /**
     * 加载任务执行信息
     */
    private void loadJobDetailExec() {
        if (RedisUtil.lock(JOB_DETAIL_LOCK_KEY)) {
            String maxIdKey = JOB_DETAIL_LOCK_KEY + ":max:id";
            Object value = RedisUtil.get(maxIdKey);
            Long maxId = Objects.isNull(value) ? 0 : Long.parseLong(value.toString());
            List<JobDetail> jobDetails = schedulingJobDetailReadService.list(maxId);
            if (!CollectionUtils.isEmpty(jobDetails)) {
                RedisUtil.set(maxIdKey, String.valueOf(jobDetails.getLast().getId()), 86400);
                jobDetails.forEach(jobDetail -> {
                    execJobDetailDTOQueue.add(createJobDetailDTO(
                            jobDetail.getJobInfoId(),
                            jobDetail.getId(),
                            jobDetail.getExecuteTime().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond()));
                });
            }

        }

    }
    
    /**
     * 创建JobDetailDTO对象
     * @param jobInfoId 任务ID
     * @param jobDetailId 任务详情ID
     * @param execTime 执行时间
     * @return JobDetailDTO对象
     */
    private JobDetailDTO createJobDetailDTO(Long jobInfoId, Long jobDetailId, Long execTime) {
        return JobDetailDTO.builder()
                .jobInfoId(jobInfoId)
                .jobDetailId(jobDetailId)
                .execTime(execTime)
                .build();
    }
}