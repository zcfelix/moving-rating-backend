package com.movie.rating.interfaces.controller;

import com.movie.rating.domain.AppException;
import com.movie.rating.interfaces.controller.representation.ErrorDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    private final HttpServletRequest request;

    @Autowired
    public ControllerAdvice(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(value = AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handleAppException(AppException e) {
        logger.info("Exception occurred, error code: {}, data: {}", e.getErrorCode(), e.getData());
        return ErrorDetail.builder()
                .code(e.getErrorCode())
                .path(request.getRequestURI())
                .timestamp(Instant.now())
                .data(e.getData())
                .build();
    }
}