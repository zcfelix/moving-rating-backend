package com.movie.rating.appservice;

import com.movie.rating.domain.Movie;
import com.movie.rating.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieAppService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieAppService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }
}
