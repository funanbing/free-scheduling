package com.free.scheduling.biz.service.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-14 13:53:35
 * @description
 */
public class ModifyJobParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 6648607346041610762L;
    /**
     * 任务id
     */
    private String id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 任务描述
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
