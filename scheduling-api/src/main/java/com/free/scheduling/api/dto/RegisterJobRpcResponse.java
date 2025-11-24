package com.free.scheduling.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-09 10:34:36
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJobRpcResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1559968063793934058L;

    /**
     * 任务id
     */
    private Long jobInfoId;
}
