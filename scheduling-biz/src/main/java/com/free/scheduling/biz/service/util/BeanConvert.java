package com.free.scheduling.biz.service.util;

import com.free.scheduling.biz.service.dto.CreateJobParam;
import com.free.scheduling.dao.entity.JobInfo;

import java.time.LocalDateTime;

/**
 * @author funanbing
 * @date 2025-11-14 15:28:04
 * @description
 */
public class BeanConvert {

    public static JobInfo convert(CreateJobParam param) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobName(param.getJobName());
        jobInfo.setJobType(param.getJobType());
        jobInfo.setStatus(param.getStatus());
        jobInfo.setJobCode(param.getJobCode());
        jobInfo.setRemark(param.getRemark());
        jobInfo.setDeleted(0);
        jobInfo.setCreateTime(LocalDateTime.now());
        jobInfo.setUpdateTime(LocalDateTime.now());
        return jobInfo;
    }
}
