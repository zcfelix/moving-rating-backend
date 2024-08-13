package com.movie.rating.infrastructure.controller.representation;

import com.movie.rating.domainmodel.ErrorCode;

import java.time.Instant;
import java.util.Map;

public record ErrorDetail(ErrorCode code, String path, Instant timestamp, Map<String, Object> data) {
}