package com.movie.rating.infrastructure.controller.representation;

import com.movie.rating.domainmodel.Genre;
import com.movie.rating.domainmodel.Movie;

import java.util.List;

import static com.movie.rating.infrastructure.controller.representation.AverageRatingFormatter.formatAverageRating;

public record MovieRepresentation(
        Integer id,
        String title,
        String year,
        Integer runtime,
        String director,
        String actors,
        String plot,
        String posterUrl,
        String averageRating,
        List<String> genres) {


    public static MovieRepresentation from(Movie movie) {
        return new MovieRepresentation(
                movie.getId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getRuntime(),
                movie.getDirector(),
                movie.getActors(),
                movie.getPlot(),
                movie.getPosterUrl(),
                formatAverageRating(movie.getAverageRating()),
                movie.getGenres().stream().map(Genre::getName).toList()
        );
    }

}