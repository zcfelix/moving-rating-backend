package com.movie.rating.interfaces.controller.representation;

import com.movie.rating.domain.Rating;

public record RatingRepresentation(Integer movieId, Integer score) {
    public static RatingRepresentation from(Rating rating) {
        return new RatingRepresentation(rating.getMovie().getId(), rating.getScore());
    }
}