package com.movie.rating.interfaces.controller;

import com.movie.rating.appservice.MovieAppService;
import com.movie.rating.interfaces.controller.representation.MovieRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {


    private final MovieAppService movieService;

    @Autowired
    public MovieController(MovieAppService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieRepresentation>> listMovies() {
        return ResponseEntity.ok(movieService.listMovies().stream().map(MovieRepresentation::from).toList());
    }
}
