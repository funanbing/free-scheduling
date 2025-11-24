package com.free.scheduling.biz.service.dto;

import com.free.scheduling.dao.entity.JobInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-17 09:34:14
 * @description
 */
@Getter
@Setter
@ToString
public class JobPageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4182465671164597811L;
    /**
     * 任务id
     */
    private String id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务编码
     */
    private String jobCode;
    /**
     * 任务类型
     */
    private Integer jobType;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;

    public JobPageDTO(JobInfo info) {
        this.id = info.getId().toString();
        this.jobName = info.getJobName();
        this.jobCode = info.getJobCode();
        this.jobType = info.getJobType();
        this.status = info.getStatus();
        this.remark = info.getRemark();
        this.createTime = info.getCreateTime().toString();
    }
}
