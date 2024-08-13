package com.movie.rating.interfaces.controller;

import com.movie.rating.domain.AppException;
import com.movie.rating.domain.ErrorCode;
import com.movie.rating.interfaces.controller.representation.ErrorDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    private final HttpServletRequest request;

    @Autowired
    public ControllerAdvice(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ErrorDetail> handleAppException(AppException e) {
        logger.info("Exception occurred, error code: {}, data: {}", e.getErrorCode(), e.getData());
        return ResponseEntity
                .status(e.getErrorCode().getCode())
                .body(new ErrorDetail(
                        e.getErrorCode(),
                        request.getRequestURI(),
                        Instant.now(),
                        e.getData())
                );
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDetail> handleOtherException(Exception e) {
        logger.info("Error occurred, error message: {}", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDetail(
                        ErrorCode.INTERNAL_ERROR,
                        request.getRequestURI(),
                        Instant.now(),
                        Map.of("message", e.getMessage()))
                );
    }
}