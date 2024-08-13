package com.movie.rating.interfaces.controller.representation;

import com.movie.rating.domain.Movie;

import java.text.DecimalFormat;

public record MovieRepresentation(
        Integer id,
        String title,
        Integer runtime,
        String director,
        String actors,
        String plot,
        String posterUrl,
        String averageRating) {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");

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

    private static String formatAverageRating(Double averageRating) {
        return averageRating != null ? DECIMAL_FORMAT.format(averageRating) : "0.0";
    }
}