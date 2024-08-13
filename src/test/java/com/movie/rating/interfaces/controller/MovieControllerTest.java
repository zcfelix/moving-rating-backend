package com.movie.rating.interfaces.controller;

import com.movie.rating.appservice.MovieAppService;
import com.movie.rating.domain.service.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@Import({ControllerAdvice.class, MovieAppService.class})
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 11})
    void should_return_400_when_rating_with_invalid_score(int score) throws Exception {
        mockMvc.perform(post("/movies/1/ratings")
                        .contentType("application/json")
                        .content("""
                                {"score": %d}""".formatted(score)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_RATING"))
                .andExpect(jsonPath("$.path").value("/movies/1/ratings"))
                .andExpect(jsonPath("$.data.score").value("Score must be between 1 and 10"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void should_return_404_when_rating_an_non_existed_movie() throws Exception {
        when(movieRepository.findById(1)).thenReturn(Optional.empty());
        mockMvc.perform(post("/movies/1/ratings")
                        .contentType("application/json")
                        .content("""
                                {"score": 5}"""))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("MOVIE_NOT_EXIST"))
                .andExpect(jsonPath("$.path").value("/movies/1/ratings"))
                .andExpect(jsonPath("$.data.movieId").value(1))
                .andExpect(jsonPath("$.timestamp").exists());
    }

}