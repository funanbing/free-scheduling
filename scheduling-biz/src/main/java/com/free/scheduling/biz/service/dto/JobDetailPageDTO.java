package com.free.scheduling.biz.service.dto;

import com.free.scheduling.dao.entity.JobDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author funanbing
 * @date 2025-11-17 14:11:35
 * @description
 */
@Getter
@Setter
@ToString
public class JobDetailPageDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2160255130283307710L;

    /**
     * 任务明细id
     */
    private String id;
    /**
     * 任务id
     */
    private String jobInfoId;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务明细名称
     */
    private String jobDetailName;
    /**
     * 任务编码
     */
    private String jobDetailCode;
    /**
     * 执行状态
     * 1:待执行 2：执行中 3：执行成功 4：执行失败
     */
    private Integer executeStatus;
    /**
     * 执行时间
     */
    private LocalDateTime executeTime;
    /**
     * 任务明细描述
     */
    private String remark;

    public JobDetailPageDTO(JobDetail detail) {
        this.id = detail.getId().toString();
        this.jobInfoId = detail.getJobInfoId().toString();
        this.jobName = detail.getJobName();
        this.jobDetailName = detail.getJobDetailName();
        this.jobDetailCode = detail.getJobDetailCode();
        this.executeStatus = detail.getExecuteStatus();
        this.executeTime = detail.getExecuteTime();
        this.remark = detail.getRemark();
    }
}
