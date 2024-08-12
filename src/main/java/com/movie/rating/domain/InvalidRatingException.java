package com.movie.rating.domain;

import java.util.Map;

public class InvalidRatingException extends AppException {
    public InvalidRatingException(Map<String, Object> data) {
        super(ErrorCode.INVALID_RATING, data);
    }
}

