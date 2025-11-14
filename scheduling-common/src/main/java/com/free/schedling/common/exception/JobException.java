package com.free.schedling.common.exception;

import com.free.schedling.common.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * @author funanbing
 * @date 2025-11-14 15:32:16
 * @description
 */
@Setter
@Getter
public class JobException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 3625819927790507832L;

    private String errorMsg;

    private Integer code;


    public JobException(String errorMsg) {
        super("[JobException] code:500,message:" + errorMsg);
        this.errorMsg = errorMsg;
        this.code = 500;
    }

    public JobException(ErrorCode errorCode) {
        super("[JobException] code:" + errorCode.getCode() + ",message:" + errorCode.getErrorMsg());
        this.errorMsg = errorCode.getErrorMsg();
        this.code = errorCode.getCode();
    }
}
