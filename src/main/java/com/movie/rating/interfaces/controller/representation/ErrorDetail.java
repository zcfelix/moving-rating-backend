package com.movie.rating.interfaces.controller.representation;

import com.movie.rating.domain.ErrorCode;

import java.time.Instant;
import java.util.Map;

public record ErrorDetail(ErrorCode code, String path, Instant timestamp, Map<String, Object> data) {
}