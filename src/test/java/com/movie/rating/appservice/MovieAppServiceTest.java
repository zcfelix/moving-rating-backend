package com.movie.rating.appservice;

import com.movie.rating.domain.InvalidRatingException;
import com.movie.rating.domain.Movie;
import com.movie.rating.domain.MovieNotExistException;
import com.movie.rating.domain.service.MovieRepository;
import com.movie.rating.interfaces.controller.request.RatingRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieAppServiceTest {

    @InjectMocks
    private MovieAppService movieAppService;

    @Mock
    private MovieRepository movieRepository;

    @Nested
    class ListMoviesTest {
        @ParameterizedTest
        @CsvSource({
                "0, 1",
                "1, 0",
                "1, -1",
        })
        void should_return_empty_when_query_movies_with_incorrect_page_number_or_page_size(int pageNumber, int pageSize) {
            assertTrue(movieAppService.listMovies(pageNumber, pageSize, "").isEmpty());
        }

        @Test
        void should_return_page_of_movies_when_query_movies_with_page_number_1_and_page_size_2() {
            var movie1 = mock(Movie.class);
            var movie2 = mock(Movie.class);
            when(movieRepository.findAll(PageRequest.of(0, 2)))
                    .thenReturn(new PageImpl<>(List.of(movie1, movie2)));

            Page<Movie> page = movieAppService.listMovies(1, 2, "");
            assertEquals(2, page.getNumberOfElements());
            assertEquals(2, page.getContent().size());
            assertEquals(1, page.getTotalPages());
        }
    }

    @Nested
    class RateMovieTest {
        @Test
        void should_throw_exception_when_movie_not_exists() {
            when(movieRepository.findById(1)).thenReturn(Optional.empty());
            assertThrows(MovieNotExistException.class,
                    () -> movieAppService.rateMovie(1, new RatingRequest(5)));
        }


        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 11})
        void should_throw_invalid_rating_exception_when_rating_movie_with_invalid_score(int score) {
            assertThrows(InvalidRatingException.class,
                    () -> movieAppService.rateMovie(1, new RatingRequest(score)));
        }

        @Test
        void should_add_movie_rating_successful() {
            var movie = mock(Movie.class);
            when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

            movieAppService.rateMovie(1, new RatingRequest(5));

            verify(movie).addRating(argThat(rating -> rating.getScore() == 5));
            verify(movieRepository).save(movie);
        }
    }

}