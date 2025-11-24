package com.free.scheduling.biz.service.util;

import com.free.schedling.common.enums.DeletedEnum;
import com.free.scheduling.biz.service.dto.CreateJobDetailParam;
import com.free.scheduling.biz.service.dto.CreateJobParam;
import com.free.scheduling.dao.entity.JobDetail;
import com.free.scheduling.dao.entity.JobInfo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author funanbing
 * @date 2025-11-14 15:28:04
 * @description
 */
public class BeanConvert {

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 创建任务
     *
     * @param param
     * @return
     */
    public static JobInfo convert(CreateJobParam param) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobName(param.getJobName());
        jobInfo.setJobType(param.getJobType());
        jobInfo.setStatus(param.getStatus());
        jobInfo.setJobCode(param.getJobCode());
        jobInfo.setRemark(param.getRemark());
        jobInfo.setDeleted(DeletedEnum.NOT_DELETE.getCode());
        jobInfo.setCreateTime(LocalDateTime.now());
        jobInfo.setUpdateTime(LocalDateTime.now());
        return jobInfo;
    }

    /**
     * 创建任务详情
     *
     * @param param
     * @return
     */
    public static JobDetail convert(CreateJobDetailParam param) {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setJobInfoId(Long.parseLong(param.getJobInfoId()));
        jobDetail.setJobDetailName(param.getJobDetailName());
        jobDetail.setJobDetailCode(param.getJobDetailCode());
        jobDetail.setExecuteTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(param.getExecuteTime()), ZoneId.systemDefault()));
        jobDetail.setRemark(param.getRemark());
        jobDetail.setDeleted(DeletedEnum.NOT_DELETE.getCode());
        jobDetail.setCreateTime(LocalDateTime.now());
        jobDetail.setUpdateTime(LocalDateTime.now());
        return jobDetail;
    }
}