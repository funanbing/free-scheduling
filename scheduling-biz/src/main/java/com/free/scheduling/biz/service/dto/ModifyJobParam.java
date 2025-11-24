package com.free.scheduling.biz.service.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-14 13:53:35
 * @description
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyJobParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 6648607346041610762L;
    /**
     * 任务id
     */
    private String id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 任务描述
     */
    private String remark;

}
