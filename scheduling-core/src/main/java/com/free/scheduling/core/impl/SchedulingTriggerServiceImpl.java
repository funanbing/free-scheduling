package com.free.scheduling.core.impl;

import com.free.scheduling.api.dto.RegisterJobDetailRpcRequest;
import com.free.scheduling.api.dto.RegisterJobRpcRequest;
import com.free.scheduling.core.SchedulingTriggerService;
import com.free.scheduling.core.dto.JobDetail;
import com.free.scheduling.core.dto.JobInfo;
import com.free.scheduling.core.enums.JobStatusEnum;
import com.free.scheduling.core.exception.TriggerException;
import com.free.scheduling.core.generic.SchedulingInvokeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author funanbing
 * @date 2025-10-09 17:18:55
 * @description
 */
@Service
@RequiredArgsConstructor
public class SchedulingTriggerServiceImpl implements SchedulingTriggerService {


    private final Map<String,JobInfo> jobInfoMap = new ConcurrentHashMap<>();

    private final LinkedBlockingQueue<JobDetail> execJobDetailQueue = new LinkedBlockingQueue<>();

    private final  ThreadPoolExecutor TriggerPool = new ThreadPoolExecutor(
            10,
            200,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000),
            r -> new Thread(r, "JobTriggerPool-" + r.hashCode()));
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private final static Long INTERVAL_TIME = 3000L;

    private final SchedulingInvokeService schedulingInvokeService;

    @PostConstruct
    public void init() {
        runExecJobDetail();
    }

    @SneakyThrows
    @Override
    public boolean trigger(RegisterJobRpcRequest request) {
        if (Objects.isNull(request)) {
            throw new TriggerException("trigger job is null");
        }
        if (!StringUtils.hasText(request.getJobId())) {
            throw new TriggerException("jobId is null");
        }
        if (!jobInfoMap.containsKey(request.getJobId())) {
            jobInfoMap.put(request.getJobId(), JobInfo.builder()
                    .jobId(request.getJobId())
                    .jobName(request.getJobName())
                    .jobType(request.getJobType())
                    .status(request.getStatus())
                    .callback(request.getCallback())
                    .build());
        }
        return true;
    }

    @SneakyThrows
    @Override
    public boolean exec(RegisterJobDetailRpcRequest request) {
        if (Objects.isNull(request)) {
            throw new TriggerException("job detail is null");
        }
        if (!StringUtils.hasText(request.getJobId())) {
            throw new TriggerException("jobId is null");
        }
        if (jobInfoMap.containsKey(request.getJobId())) {
            execJobDetailQueue.add(JobDetail.builder()
                    .jobId(request.getJobId())
                    .jobDetailId(request.getJobDetailId())
                    .execTime(request.getExecTime())
                    .build());
        }
        return true;
    }

    private void runExecJobDetail() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("poll jobDetail");
            while (!CollectionUtils.isEmpty(execJobDetailQueue)) {
                JobDetail jobDetail = execJobDetailQueue.poll();
                if (Objects.isNull(jobDetail)) {
                    continue;
                }
                System.out.println("exec jobDetail :"+jobDetail.getJobDetailId());
                JobInfo jobInfo = jobInfoMap.get(jobDetail.getJobId());
                if (Objects.nonNull(jobInfo) && JobStatusEnum.RUNNING.getCode().equals(jobInfo.getStatus())) {
                    long now = System.currentTimeMillis()/1000;
                    if (now >= jobDetail.getExecTime() || now >= jobDetail.getExecTime()+ INTERVAL_TIME) {
                        TriggerPool.execute(() -> {
                            CompletableFuture<Object> future = schedulingInvokeService.invoke(jobInfo.getCallback().getInterfaceName(),
                                    jobInfo.getCallback().getMethod(),
                                    jobInfo.getCallback().getParamTypes(),
                                    jobInfo.getCallback().getArgs());
                            Object object = null;
                            try {
                                object = future.get(2, TimeUnit.SECONDS);
                                System.out.println("exec jobDetail :"+jobDetail.getJobDetailId()+" result:"+object);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            }
        }, 0,2, TimeUnit.SECONDS);
    }
}
