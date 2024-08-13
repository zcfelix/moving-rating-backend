package com.movie.rating.appservice;

import com.movie.rating.domain.model.Movie;
import com.movie.rating.domain.model.MovieNotExistException;
import com.movie.rating.domain.model.Rating;
import com.movie.rating.domain.repository.MovieRepository;
import com.movie.rating.interfaces.controller.request.RatingRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MovieAppService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieAppService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> listMovies(Integer pageNumber, Integer pageSize, String titleToSearch) {
        if (pageNumber < 1 || pageSize <= 0) {  // 1-based page number
            return Page.empty();
        }

        // repo use 0-based page index
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        if (titleToSearch == null || titleToSearch.isBlank()) {
            return movieRepository.findAll(pageRequest);
        } else {
            return movieRepository.findByTitleContainingIgnoreCase(titleToSearch, pageRequest);
        }
    }


    @Transactional
    public Rating rateMovie(Integer movieId, RatingRequest ratingRequest) {
        Rating rating = Rating.create(ratingRequest.score()); // has business validation when create the rating object

        Movie movie = movieRepository
                .findById(movieId)
                .orElseThrow(() -> new MovieNotExistException(Map.of("movieId", movieId)));

        Rating savedRating = movie.addRating(rating);
        movieRepository.save(movie);

        return savedRating;
    }
}
