package com.free.scheduling.biz.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-17 09:37:19
 * @description
 */
@Getter
@Setter
@ToString
public class JobPageParam implements Serializable {


    @Serial
    private static final long serialVersionUID = 4443710917846060864L;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 每页数量
     */
    private Integer size;
}
