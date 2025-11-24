package com.free.scheduling.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-09 10:42:30
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJobDetailRpcRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -2143106627941208507L;
    /**
     * 任务id
     */
    private Long jobInfoId;
    /**
     * 任务详情名称
     */
    private String jobDetailName;
    /**
     * 任务详情Code
     */
    private String jobDetailCode;
    /**
     * 任务详情描述
     */
    private String remark;
    /**
     * 执行时间
     */
    private Long execTime;
}
