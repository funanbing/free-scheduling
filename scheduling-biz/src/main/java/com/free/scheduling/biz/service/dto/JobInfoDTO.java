package com.free.scheduling.biz.service.dto;

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
public class JobInfoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8400604734075053020L;
    /**
     * 任务ID
     */
    private Long jobInfoId;

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
     * 任务状态
     */
    private Integer status;
    /**
     * 回调信息
     */
    private JobCallBackDTO jobCallBack;

}
