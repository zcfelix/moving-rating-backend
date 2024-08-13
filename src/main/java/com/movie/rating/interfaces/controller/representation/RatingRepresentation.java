package com.movie.rating.interfaces.controller.representation;

import com.movie.rating.domain.Rating;

import static com.movie.rating.interfaces.controller.representation.AverageRatingFormatter.formatAverageRating;

public record RatingRepresentation(Integer movieId, Integer score, String averageRating) {

    public static RatingRepresentation from(Rating rating) {

        return new RatingRepresentation(
                rating.getMovie().getId(),
                rating.getScore(),
                formatAverageRating(rating.getMovie().getAverageRating())
        );
    }
}