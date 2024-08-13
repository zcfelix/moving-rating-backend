package com.movie.rating.interfaces.controller.representation;

import com.movie.rating.domain.model.Movie;

import static com.movie.rating.interfaces.controller.representation.AverageRatingFormatter.formatAverageRating;

public record MovieRepresentation(
        Integer id,
        String title,
        Integer runtime,
        String director,
        String actors,
        String plot,
        String posterUrl,
        String averageRating) {


    public static MovieRepresentation from(Movie movie) {
        return new MovieRepresentation(
                movie.getId(),
                movie.getTitle(),
                movie.getRuntime(),
                movie.getDirector(),
                movie.getActors(),
                movie.getPlot(),
                movie.getPosterUrl(),
                formatAverageRating(movie.getAverageRating())
        );
    }

}