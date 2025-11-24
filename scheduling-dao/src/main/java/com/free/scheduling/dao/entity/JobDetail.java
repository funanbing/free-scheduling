package com.free.scheduling.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author funanbing
 * @date 2025-11-17 13:36:35
 * @description
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "job_detail")
public class JobDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 897557638107575736L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 任务名称
     */
    @Column(name = "job_info_id")
    private Long jobInfoId;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务明细名称
     */
    @Column(name = "job_detail_name")
    private String jobDetailName;

    /**
     * 任务编码
     */
    @Column(name = "job_detail_code")
    private String jobDetailCode;

    /**
     * 执行状态
     */
    @Column(name = "execute_status")
    private Integer executeStatus;

    /**
     * 执行时间
     */
    @Column(name = "execute_time")
    private LocalDateTime executeTime;

    /**
     * 任务明细描述
     */
    @Column(name = "remark")
    private String remark;

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
}
