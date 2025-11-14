package com.free.scheduling.biz.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-14 13:43:56
 * @description
 */
@Getter
@Setter
@ToString
public class CreateJobParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -8211016658013891811L;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空")
    @Size(max = 30, message = "任务名称长度不能超过30")
    private String jobName;
    /**
     * 任务类型
     */
    @NotNull(message = "任务类型不能为空")
    private Integer jobType;
    /**
     * 状态
     */
    @NotNull(message = "任务状态不能为空")
    private Integer status;
    /**
     * 任务编码
     * 所有任务唯一
     */
    @NotBlank(message = "任务编码不能为空")
    @Size(max = 30, message = "任务名称长度不能超过30")
    private String jobCode;
    /**
     * 任务描述
     */
    private String remark;
}
