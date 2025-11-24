package com.free.scheduling.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-09 10:34:11
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJobRpcRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 78234984674684112L;
    /**
     * 任务ID
     */
    private Long jobInfoId;
    /**
     * 任务编码
     */
    private String jobCode;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务类型
     */
    private Integer jobType;

    /**
     * 任务状态
     */
    private Integer status;
    /**
     * 任务描述
     */
    private String remark;
    /**
     * 回调方法
     */
    private JobCallBack callback;
}
