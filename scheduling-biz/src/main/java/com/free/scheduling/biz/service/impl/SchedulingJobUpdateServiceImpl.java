package com.free.scheduling.biz.service.impl;

import com.free.schedling.common.enums.DeletedEnum;
import com.free.schedling.common.enums.ErrorCode;
import com.free.scheduling.biz.service.SchedulingJobUpdateService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.CreateJobParam;
import com.free.scheduling.biz.service.dto.DelJobParam;
import com.free.scheduling.biz.service.dto.ModifyJobParam;
import com.free.scheduling.biz.service.util.BeanConvert;
import com.free.schedling.common.exception.JobException;
import com.free.scheduling.dao.entity.JobInfo;
import com.free.scheduling.dao.repository.JobInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author funanbing
 * @date 2025-11-14 13:58:48
 * @description
 */
@Slf4j
@Service
public class SchedulingJobUpdateServiceImpl implements SchedulingJobUpdateService {

    private final JobInfoRepository jobInfoRepository;
    private final RedissonClient redissonClient;

    @Autowired
    public SchedulingJobUpdateServiceImpl(JobInfoRepository jobInfoRepository,
                                          RedissonClient redissonClient) {
        this.jobInfoRepository = jobInfoRepository;
        this.redissonClient = redissonClient;
    }

    @Override
    public ResponseEntity<Boolean> createJob(CreateJobParam param) {
        save(param);
        return ResponseEntity.success(true);
    }

    private JobInfo save(CreateJobParam param) {
        int count = jobInfoRepository.validJob(param.getJobName(), param.getJobCode());
        Assert.isTrue(count == 0, "任务名称或任务编码已存在");
        try {
            return jobInfoRepository.save(BeanConvert.convert(param));
        } catch (JobException e) {
            throw new JobException(ErrorCode.JOB_CREATE_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteJob(DelJobParam param) {
        Optional<JobInfo> jobInfo = jobInfoRepository.findById(Long.parseLong(param.getId()));
        if (jobInfo.isPresent() && jobInfo.get().getJobCode().equals(param.getJobCode())) {
            JobInfo job = jobInfo.get();
            job.setDeleted(DeletedEnum.DELETE.getCode());
            job.setUpdateTime(LocalDateTime.now());
            jobInfoRepository.save(job);
            return ResponseEntity.success(true);
        }
        return ResponseEntity.fail(ErrorCode.JOB_DELETE_ERROR_NULL);
    }

    @Override
    public ResponseEntity<Boolean> modifyJob(ModifyJobParam param) {
        Optional<JobInfo> jobInfo = jobInfoRepository.findById(Long.parseLong(param.getId()));
        if (jobInfo.isPresent()) {
            JobInfo job = jobInfo.get();
            job.setJobName(param.getJobName());
            job.setStatus(param.getStatus());
            job.setRemark(param.getRemark());
            job.setUpdateTime(LocalDateTime.now());
            jobInfoRepository.save(job);
            return ResponseEntity.success(true);
        } else {
            return ResponseEntity.fail(ErrorCode.JOB_MODIFY_ERROR_NULL);
        }
    }

    @Override
    public Long registerJob(CreateJobParam param) {
        return save(param).getId();
    }
}
