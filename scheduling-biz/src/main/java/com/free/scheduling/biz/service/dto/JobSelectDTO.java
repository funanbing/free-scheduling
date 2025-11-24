package com.free.scheduling.biz.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-18 09:45:30
 * @description
 */
@Getter
@Setter
@ToString
public class JobSelectDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7535681011487815673L;
    /**
     * 任务id
     */
    private String jobInfoId;
    /**
     * 任务名称
     */
    private String jobName;
}
