package com.free.scheduling.biz.service;

import com.free.schedling.common.enums.ErrorCode;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.CreateJobParam;
import com.free.scheduling.biz.service.dto.DelJobParam;
import com.free.scheduling.biz.service.dto.ModifyJobParam;
import com.free.scheduling.biz.service.util.BeanConvert;
import com.free.schedling.common.exception.JobException;
import com.free.scheduling.dao.repository.JobInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author funanbing
 * @date 2025-11-14 13:58:48
 * @description
 */
@Slf4j
@Service
public class SchedulingJobUpdateServiceImpl implements SchedulingJobUpdateService {

    private final JobInfoRepository jobInfoRepository;

    @Autowired
    public SchedulingJobUpdateServiceImpl(JobInfoRepository jobInfoRepository) {
        this.jobInfoRepository = jobInfoRepository;
    }

    @Override
    public ResponseEntity<Boolean> createJob(CreateJobParam param) {
        int count = jobInfoRepository.validJob(param.getJobName(), param.getJobCode());
        Assert.isTrue(count == 0, "任务名称或任务编码已存在");
        try {
            jobInfoRepository.save(BeanConvert.convert(param));
            return ResponseEntity.success(true);
        } catch (JobException e) {
            throw new JobException(ErrorCode.JOB_CREATE_ERROR);
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteJob(DelJobParam param) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> modifyJob(ModifyJobParam param) {
        return null;
    }
}
