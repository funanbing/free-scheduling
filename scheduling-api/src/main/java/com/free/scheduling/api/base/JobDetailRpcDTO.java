package com.free.scheduling.api.base;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-11 14:29:13
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDetailRpcDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4460881205845091461L;
    /**
     * 是否有下一次执行
     */
    private boolean next;
    /**
     * 下一次执行时间
     */
    private Long execTime;
}
