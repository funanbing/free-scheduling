package com.free.scheduling.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-09 10:43:57
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJobDetailRpcResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7356918222472148388L;
    /**
     * 任务详情id
     */
    private String jobDetailId;
}
