package com.maxcare.common;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiException extends RuntimeException {

    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() { return errorCode; }

    public HttpStatus getStatus() { return errorCode.status(); }

    public String getCode() { return errorCode.name(); }
}