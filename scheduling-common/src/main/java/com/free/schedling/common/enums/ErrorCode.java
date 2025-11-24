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
    JOB_CREATE_ERROR_REGISTER(10_001_99, "JOB CREATE ERROR,REGISTER JOB FAIL"),
    JOB_MODIFY_ERROR(10_002, "JOB MODIFY ERROR"),
    JOB_MODIFY_ERROR_NULL(10_002_01, "JOB MODIFY ERROR,JOB NOT EXIST"),
    JOB_DELETE_ERROR(10_003, "JOB DELETE ERROR"),
    JOB_DELETE_ERROR_NULL(10_003_01, "JOB DELETE ERROR,JOB NOT EXIST"),
    JOB_QUERY_ERROR(10_004, "JOB QUERY ERROR"),
    JOB_QUERY_ERROR_NULL(10_004_01, "JOB QUERY ERROR,JOB NOT EXIST"),




    PARAM_VALIDATION_ERROR(99_999, "PARAM VALIDATION ERROR"),
    ;


    private final Integer code;
    private final String errorMsg;

    ErrorCode(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }
}
