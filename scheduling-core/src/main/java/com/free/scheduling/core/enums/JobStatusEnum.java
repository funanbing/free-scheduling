package com.free.scheduling.core.enums;

import lombok.Getter;

/**
 * @author funanbing
 * @date 2025-10-10 09:42:28
 * @description
 */
@Getter
public enum JobStatusEnum {

    RUNNING(1, "运行中"),
    STOP(2, "停止"),
    DELETE(3, "删除");

    private final Integer code;
    private final String msg;
    JobStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
