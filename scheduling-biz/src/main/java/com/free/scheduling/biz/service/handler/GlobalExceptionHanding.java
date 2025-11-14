package com.free.scheduling.biz.service.handler;

import com.free.schedling.common.enums.ErrorCode;
import com.free.schedling.common.exception.JobException;
import com.free.scheduling.biz.service.base.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author funanbing
 * @date 2025-11-14 16:22:29
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHanding {

    @ExceptionHandler({JobException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Boolean> handleException(JobException ex) {
        return ResponseEntity.fail(ex.getCode(), ex.getErrorMsg());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Boolean> handleValidationException(MethodArgumentNotValidException ex) {
        StringBuilder errorMsg = new StringBuilder();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errorMsg.append(error.getDefaultMessage()).append("; "));
        return ResponseEntity.fail(ErrorCode.PARAM_VALIDATION_ERROR.getCode(), errorMsg.toString());
    }


    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Boolean> handleValidationException(IllegalArgumentException ex) {
        return ResponseEntity.fail(ErrorCode.PARAM_VALIDATION_ERROR.getCode(), ex.getMessage());
    }
}
