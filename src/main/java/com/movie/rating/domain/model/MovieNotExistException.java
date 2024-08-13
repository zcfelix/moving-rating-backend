package com.movie.rating.domain.model;

import java.util.Map;

public class MovieNotExistException extends AppException {
    public MovieNotExistException(Map<String, Object> data) {
        super(ErrorCode.MOVIE_NOT_EXIST, data);
    }
}
