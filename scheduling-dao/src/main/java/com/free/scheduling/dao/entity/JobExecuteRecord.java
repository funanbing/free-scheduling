package com.free.scheduling.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 
 * @date 2025-11-17
 * @description 任务执行记录实体类
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "job_execute_record")
public class JobExecuteRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 任务主键
     */
    @Column(name = "job_info_id")
    private Long jobInfoId;

    /**
     * 任务名称
     */
    @Column(name = "job_info_name")
    private String jobInfoName;

    /**
     * 任务明细主键
     */
    @Column(name = "job_detail_id")
    private Long jobDetailId;

    /**
     * 任务明细名称
     */
    @Column(name = "job_detail_name")
    private String jobDetailName;

    /**
     * 执行结果
     */
    @Column(name = "execute_result")
    private Integer executeResult;

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
     * 删除标记
     */
    @Column(name = "deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;
}