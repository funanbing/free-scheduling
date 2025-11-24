package com.free.scheduling.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author funanbing
 * @date 2025-10-29 15:12:02
 * @description
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "job_info")
public class JobInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -5416994418479411101L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务编码
     */
    @Column(name = "job_code")
    private String jobCode;

    /**
     * 任务类型
     */
    @Column(name = "job_type")
    private Integer jobType;
    /**
     * 任务描述
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 删除状态
     */
    @Column(name = "deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    // 添加了以下构造函数
    public JobInfo() {
    }

    public JobInfo(Long id, String jobName, String jobCode, Integer jobType, Integer status, String remark, LocalDateTime createTime) {
        this.id = id;
        this.jobName = jobName;
        this.jobCode = jobCode;
        this.jobType = jobType;
        this.status = status;
        this.remark = remark;
        this.createTime = createTime;
}

}