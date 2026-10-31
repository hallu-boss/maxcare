package com.maxcare.common;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    VALIDATION_FAILED(HttpStatus.BAD_REQUEST),

    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT);

    private final HttpStatus status;

    ErrorCode(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus status() {
        return status;
    }
}
