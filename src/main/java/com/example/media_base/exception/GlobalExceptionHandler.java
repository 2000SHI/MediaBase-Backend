package com.example.media_base.exception;

import com.example.media_base.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e) {
        e.printStackTrace();
        String msg = StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "Internal Error";
        return Result.failure(msg);
    }
}
