package com.movie.rating.infrastructure.controller;

import com.movie.rating.appservice.MovieAppService;
import com.movie.rating.domainmodel.Movie;
import com.movie.rating.infrastructure.controller.representation.ErrorDetail;
import com.movie.rating.infrastructure.controller.representation.MovieRepresentation;
import com.movie.rating.infrastructure.controller.representation.PageRepresentation;
import com.movie.rating.infrastructure.controller.representation.RatingRepresentation;
import com.movie.rating.infrastructure.controller.request.RatingRequest;
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
                                                                "genres": ["Action", "Adventure", "Sci-Fi"],
                                                                "year": "2010",
                                                                "runtime": 148,
                                                                "director": "Christopher Nolan",
                                                                "actors": "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page",
                                                                "plot": "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                                                                "posterUrl": "https://example.com/inception.jpg"
                                                            },
                                                            {
                                                                "id": 2,
                                                                "title": "The Matrix",
                                                                "genres": ["Action", "Sci-Fi"],
                                                                "year": "1993",
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
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RatingRequest.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "score": 5
                                    }
                            """))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Rating created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RatingRepresentation.class),
                                    examples = @ExampleObject(value = """
                                                    {
                                                        "movieId": 1,
                                                        "score": 5,
                                                        "averageRating": "5.0"
                                                    }
                                            """))),
                    @ApiResponse(responseCode = "400", description = "Invalid rating score",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class),
                                    examples = @ExampleObject(value = """
                                                {
                                                    	"code": "INVALID_RATING",
                                                        "path": "/movies/1/ratings",
                                                        "timestamp": "2024-08-13T16:12:02.124414Z",
                                                        "data": {
                                                        	"score": "Score must be between 1 and 10"
                                                        }
                                                }
                                        """))),
                    @ApiResponse(responseCode = "404", description = "Movie not found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDetail.class),
                                    examples = @ExampleObject(value = """
                                                {
                                                    	"code": "MOVIE_NOT_EXIST",
                                                        "path": "/movies/0/ratings",
                                                        "timestamp": "2024-08-13T16:12:37.937628Z",
                                                        "data": {
                                                        	"movieId": 0
                                                        }
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
