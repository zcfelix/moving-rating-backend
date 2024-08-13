package com.movie.rating.domainmodel;

public enum ErrorCode {
    MOVIE_NOT_EXIST(404),
    INVALID_RATING(400),
    INTERNAL_ERROR(500);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
