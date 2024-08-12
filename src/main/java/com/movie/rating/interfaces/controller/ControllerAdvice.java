package com.movie.rating.interfaces.controller;

import com.movie.rating.domain.AppException;
import com.movie.rating.interfaces.controller.representation.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = AppException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorDetail handleAppException(AppException e) {
        return ErrorDetail.builder()
                .code(e.getErrorCode())
                .timestamp(Instant.now())
                .data(e.getData())
                .build();
    }
}