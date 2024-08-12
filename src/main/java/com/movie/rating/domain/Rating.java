package com.movie.rating.domain;

import java.util.Map;

public class Rating {
    private Integer score;

    public Rating(Integer score) {
        this.score = score;
    }

    public static Rating create(Integer rating) {
        if (rating < 1 || rating > 10) {
            throw new InvalidRatingException(Map.of("score", "Score must be between 1 and 10"));
        }
        return new Rating(rating);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating rating1)) return false;

        return score.equals(rating1.score);
    }

    @Override
    public int hashCode() {
        return score.hashCode();
    }
}
