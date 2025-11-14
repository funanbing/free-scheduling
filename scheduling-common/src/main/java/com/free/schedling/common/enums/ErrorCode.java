package com.free.schedling.common.enums;

import lombok.Getter;

/**
 * @author funanbing
 * @date 2025-11-14 15:53:36
 * @description
 */
@Getter
public enum ErrorCode {

    SUCCESS(200, "SUCCESS"),
    SERVER_ERROR(500, "SERVER ERROR"),
    JOB_CREATE_ERROR(10_001, "JOB CREATE ERROR"),
    JOB_MODIFY_ERROR(10_002, "JOB MODIFY ERROR"),
    JOB_DELETE_ERROR(10_003, "JOB DELETE ERROR"),




    PARAM_VALIDATION_ERROR(99_999, "PARAM VALIDATION ERROR"),
    ;


    private final Integer code;
    private final String errorMsg;

    ErrorCode(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }
}
