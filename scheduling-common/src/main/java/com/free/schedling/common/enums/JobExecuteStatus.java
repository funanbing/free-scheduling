package com.free.schedling.common.enums;

import lombok.Getter;

/**
 * @author funanbing
 * @date 2025-11-17 14:47:30
 * @description
 */
@Getter
public enum JobExecuteStatus {

    WAITING(0, "等待执行"),
    RUNNING(1, "正在执行"),
    SUCCESS(2, "执行成功"),
    FAIL(3, "执行失败"),
    CANCEL(4, "取消执行");

    private final Integer code;
    private final String msg;

    JobExecuteStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
