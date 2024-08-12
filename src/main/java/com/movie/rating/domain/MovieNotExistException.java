package com.movie.rating.domain;

import java.util.Map;

public class MovieNotExistException extends AppException {
    public MovieNotExistException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode, data);
    }
}
