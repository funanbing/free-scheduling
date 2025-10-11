package com.free.scheduling.api.base;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-09-26 11:36:16
 * @description
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingUpdateRpcRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7393499786246832491L;
    /**
     * 任务id
     */
    private String jobId;
    /**
     * 任务状态
     */
    private Integer status;
}
