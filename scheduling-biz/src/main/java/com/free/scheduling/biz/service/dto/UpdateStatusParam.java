package com.free.scheduling.biz.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-18 10:38:10
 * @description
 */
@Getter
@Setter
@ToString
public class UpdateStatusParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 9108867758710180881L;
    /**
     * 任务详情id
     */
    @NotBlank(message = "任务详情id不能为空")
    private String jobDetailId;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
}
