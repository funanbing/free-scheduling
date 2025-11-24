package com.free.scheduling.biz.service.base;

import com.free.schedling.common.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-14 13:41:38
 * @description
 */
@Setter
@Getter
public class ResponseEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1585628104674409854L;

    private int code;

    private String msg;

    private T data;

    private boolean success;

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> response = new ResponseEntity<>();
        response.setCode(200);
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public static <T> ResponseEntity<T> fail(int code, String msg) {
        ResponseEntity<T> response = new ResponseEntity<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setSuccess(code == 200);
        return response;
    }

    public static <T> ResponseEntity<T> fail(ErrorCode errorCode) {
        ResponseEntity<T> response = new ResponseEntity<>();
        response.setCode(errorCode.getCode());
        response.setMsg(errorCode.getErrorMsg());
        response.setSuccess(errorCode.getCode() == 200);
        return response;
    }
}
