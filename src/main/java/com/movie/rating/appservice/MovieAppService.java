package com.movie.rating.appservice;

import com.movie.rating.domain.Movie;
import com.movie.rating.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovieAppService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieAppService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> listMovies(Integer pageNumber, Integer pageSize) {
        if (pageNumber < 1 || pageSize <= 0) {  // 1-based page number
            return Page.empty();
        }

        // repo use 0-based page index
        return movieRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
    }
}
