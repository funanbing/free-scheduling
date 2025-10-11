package com.free.scheduling.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-10 10:00:04
 * @description
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobCallBack implements Serializable {

    @Serial
    private static final long serialVersionUID = 347968150224148841L;
    /**
     * 接口名称,完整路径
     */
    private String interfaceName;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 参数类型
     */
    private String[] paramTypes;
    /**
     * 参数
     */
    private Object[] args;
    /**
     * 额外的参数
     */
    private String extParam;
}
