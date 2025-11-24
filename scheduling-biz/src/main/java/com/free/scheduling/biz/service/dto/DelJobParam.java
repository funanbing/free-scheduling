package com.free.scheduling.biz.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-14 13:51:44
 * @description
 */
@Setter
@Getter
@ToString
public class DelJobParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 8753616955883930021L;

    @NotBlank(message = "任务id不能为空")
    private String id;

    @NotBlank(message = "任务编码不能为空")
    private String jobCode;
}
