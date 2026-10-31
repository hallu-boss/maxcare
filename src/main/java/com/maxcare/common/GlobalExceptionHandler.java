package com.maxcare.common;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException e, Locale locale) {
        System.out.println(locale);
        System.out.println(e.getCode());

        String message = messageSource.getMessage(e.getCode(), null, locale);

        ApiErrorResponse response = new ApiErrorResponse(e.getCode(), message);

        return ResponseEntity
                .status(e.getStatus())
                .body(response);
    }
}
