package com.free.scheduling.core.dto;

import com.free.scheduling.api.dto.JobCallBack;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-09 17:22:25
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -8400604734075053020L;
    /**
     * 任务ID
     */
    private String jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务类型
     */
    private String jobType;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 回调方法
     */
    private JobCallBack callback;

}
