package com.free.schedling.common.enums;

import lombok.Getter;

/**
 * @author funanbing
 * @date 2025-11-17 11:53:21
 * @description
 */
@Getter
public enum DeletedEnum {

    DELETE(1, "删除"),
    NOT_DELETE(0, "未删除");

    private final Integer code;
    private final String errorMsg;

    DeletedEnum(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }
}
