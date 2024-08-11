package com.movie.rating.interfaces.controller;

import com.movie.rating.appservice.MovieAppService;
import com.movie.rating.domain.Movie;
import com.movie.rating.interfaces.controller.representation.MovieRepresentation;
import com.movie.rating.interfaces.controller.representation.PageRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {


    private final MovieAppService movieAppService;

    @Autowired
    public MovieController(MovieAppService movieAppService) {
        this.movieAppService = movieAppService;
    }

    @GetMapping
    public ResponseEntity<PageRepresentation<MovieRepresentation>> listMovies(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        Page<Movie> page = movieAppService.listMovies(pageNumber, pageSize);
        return ResponseEntity.ok(
                new PageRepresentation<>(
                        page.map(MovieRepresentation::from).getContent(),
                        page.getNumberOfElements()
                )
        );
    }
}
