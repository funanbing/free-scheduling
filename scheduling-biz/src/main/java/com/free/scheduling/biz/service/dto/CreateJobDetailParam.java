package com.free.scheduling.biz.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-17 14:38:20
 * @description
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDetailParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 5805617846232334668L;

    /**
     * 任务id
     */
    @NotBlank(message = "任务id不能为空")
    private String jobInfoId;
    /**
     * 任务明细名称
     */
    @NotBlank(message = "任务明细名称不能为空")
    private String jobDetailName;
    /**
     * 任务编码
     */
    @NotBlank(message = "任务编码不能为空")
    private String jobDetailCode;
    /**
     * 执行时间
     */
    @NotBlank(message = "执行时间不能为空")
    private Long executeTime;
    /**
     * 任务明细描述
     */
    private String remark;
}
