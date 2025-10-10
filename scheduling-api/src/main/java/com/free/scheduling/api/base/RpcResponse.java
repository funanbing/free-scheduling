package com.free.scheduling.api.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-09-26 11:31:49
 * @description
 */
@Getter
@Setter
@ToString
public class RpcResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1585628104674409854L;

    private int code;

    private String msg;

    private T data;

    public static <T> RpcResponse<T> success(T data) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(200);
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(int code, String msg) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

}
