package com.free.scheduling.biz.service.base;

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

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> response = new ResponseEntity<>();
        response.setCode(200);
        response.setData(data);
        return response;
    }

    public static <T> ResponseEntity<T> fail(int code, String msg) {
        ResponseEntity<T> response = new ResponseEntity<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
