package com.free.scheduling.biz.service.impl;

import com.free.schedling.common.enums.JobExecuteStatus;
import com.free.schedling.common.exception.JobException;
import com.free.scheduling.biz.service.SchedulingJobDetailUpdateService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.CreateJobDetailParam;
import com.free.scheduling.biz.service.dto.UpdateStatusParam;
import com.free.scheduling.biz.service.util.BeanConvert;
import com.free.scheduling.dao.entity.JobDetail;
import com.free.scheduling.dao.entity.JobInfo;
import com.free.scheduling.dao.repository.JobDetailRepository;
import com.free.scheduling.dao.repository.JobInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.free.schedling.common.enums.ErrorCode.JOB_DELETE_ERROR_NULL;

/**
 * @author funanbing
 * @date 2025-11-17 14:23:23
 * @description
 */
@Service
public class SchedulingJobDetailUpdateServiceImpl implements SchedulingJobDetailUpdateService {

    private final JobDetailRepository jobDetailRepository;
    private final JobInfoRepository jobInfoRepository;

    @Autowired
    public SchedulingJobDetailUpdateServiceImpl(JobDetailRepository jobDetailRepository,
                                                JobInfoRepository jobInfoRepository) {
        this.jobDetailRepository = jobDetailRepository;
        this.jobInfoRepository = jobInfoRepository;
    }

    @Override
    public ResponseEntity<Boolean> createJobDetail(CreateJobDetailParam param) {
        save(param);
        return ResponseEntity.success(true);
    }

    private JobDetail save(CreateJobDetailParam param) {
        Optional<JobInfo> jobInfo = jobInfoRepository.findById(Long.valueOf(param.getJobInfoId()));
        if (jobInfo.isEmpty()) {
            throw new JobException(JOB_DELETE_ERROR_NULL);
        }
        JobDetail detail = BeanConvert.convert(param);
        detail.setJobName(jobInfo.get().getJobName());
        detail.setExecuteStatus(JobExecuteStatus.WAITING.getCode());
        return jobDetailRepository.save(detail);
    }


    @Override
    public ResponseEntity<Boolean> updateStatus(UpdateStatusParam param) {
        Optional<JobDetail> optional = jobDetailRepository.findById(Long.valueOf(param.getJobDetailId()));
        if (optional.isEmpty()) {
            return ResponseEntity.fail(JOB_DELETE_ERROR_NULL);
        }
        JobDetail jobDetail = optional.get();
        jobDetail.setExecuteStatus(param.getStatus());
        jobDetail.setUpdateTime(LocalDateTime.now());
        jobDetailRepository.save(jobDetail);
        //TODO 可执行任务状态变更后续操作
        return ResponseEntity.success(true);
    }

    @Override
    public Long registerJobDetail(CreateJobDetailParam request) {
        JobDetail jobDetail = save(request);
        return jobDetail.getId();
    }
}
