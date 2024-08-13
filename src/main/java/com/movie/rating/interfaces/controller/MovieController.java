package com.movie.rating.interfaces.controller;

import com.movie.rating.appservice.MovieAppService;
import com.movie.rating.domain.model.Movie;
import com.movie.rating.interfaces.controller.representation.MovieRepresentation;
import com.movie.rating.interfaces.controller.representation.PageRepresentation;
import com.movie.rating.interfaces.controller.representation.RatingRepresentation;
import com.movie.rating.interfaces.controller.request.RatingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    private final MovieAppService movieAppService;

    @Autowired
    public MovieController(MovieAppService movieAppService) {
        this.movieAppService = movieAppService;
    }

    @Operation(summary = "List movies", description = "Retrieve a list of movies",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PageRepresentation.class),
                                    examples = @ExampleObject(value = """
                                                    {
                                                        "content": [
                                                            {
                                                                "id": 1,
                                                                "title": "Inception",
                                                                "runtime": 148,
                                                                "director": "Christopher Nolan",
                                                                "actors": "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                                                                "plot": "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                                                                "posterUrl": "https://example.com/inception.jpg"
                                                            },
                                                            {
                                                                "id": 2,
                                                                "title": "The Matrix",
                                                                "runtime": 136,
                                                                "director": "Lana Wachowski, Lilly Wachowski",
                                                                "actors": "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
                                                                "plot": "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                                                                "posterUrl": "https://example.com/matrix.jpg"
                                                            }
                                                        ],
                                                        "totalSize": 2
                                                    }
                                            """)))
            }
    )
    @GetMapping
    public ResponseEntity<PageRepresentation<MovieRepresentation>> listMovies(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(value = "title", required = false) String title) {
        Page<Movie> page = movieAppService.listMovies(pageNumber, pageSize, title);
        return ResponseEntity.ok(
                new PageRepresentation<>(
                        page.map(MovieRepresentation::from).getContent(),
                        page.getTotalElements()
                )
        );
    }

    @Operation(summary = "Rate a movie", description = "Rate a movie with a score",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Rating created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RatingRepresentation.class),
                                    examples = @ExampleObject(value = """
                                                    {
                                                        "movieId": 1,
                                                        "score": 5
                                                    }
                                            """)))
            }
    )
    @PostMapping(path = "/{movieId}/ratings")
    public ResponseEntity<RatingRepresentation> createRating(@PathVariable("movieId") Integer movieId,
                                             @RequestBody @Valid RatingRequest ratingRequest) {
        logger.info("Received rating request for movieId: {}, with score: {}", movieId, ratingRequest.score());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RatingRepresentation.from(movieAppService.rateMovie(movieId, ratingRequest)));
    }
}
