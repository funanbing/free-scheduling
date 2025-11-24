package com.free.scheduling.biz.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-17 14:08:57
 * @description
 */
@Getter
@Setter
@ToString
public class JobDetailPageParam implements Serializable {

    @Serial
    private static final long serialVersionUID = -2160255130283307710L;

    private String jobInfoId;

    private String beginExecTime;

    private String endExecTime;

    private Integer page;

    private Integer size;
}
