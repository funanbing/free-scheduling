package com.free.schedling.common.exception;

import java.io.Serial;

/**
 * @author funanbing
 * @date 2025-11-20 10:07:48
 * @description
 */
public class CacheException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3403646005941640889L;

    public CacheException(String message) {
        super(message);
    }
}
