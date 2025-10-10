package com.free.scheduling.core.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-10-09 17:27:36
 * @description
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -3966160350848763069L;

    /**
     * 任务ID
     */
    private String jobId;

    /**
     * 任务详情ID
     */
    private String jobDetailId;

    /**
     * 执行时间
     * 时间戳精确到秒
     */
    private Long execTime;

}
