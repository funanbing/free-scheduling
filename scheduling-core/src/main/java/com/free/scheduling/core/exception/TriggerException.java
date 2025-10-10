package com.free.scheduling.core.exception;

import java.io.Serial;

/**
 * @author funanbing
 * @date 2025-10-10 09:26:22
 * @description
 */
public class TriggerException extends Exception{
    @Serial
    private static final long serialVersionUID = -3610694433185075035L;

    public TriggerException(String message) {
        super(message);
    }
}
