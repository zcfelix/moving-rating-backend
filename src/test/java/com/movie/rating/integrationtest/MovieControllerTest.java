package com.movie.rating.integrationtest;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// prepare test data throw data migrations, see files under src/test/resources/db/migration/test/
class MovieControllerTest extends AbstractControllerTest {

    @Test
    void should_list_movies() throws Exception {
        mockMvc.perform(get("/movies")
                        .param("pageNumber", "1")
                        .param("pageSize", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contents.size()").value(25)) // prepared 25 movies in test data
                .andExpect(jsonPath("$.contents[0].id").isNumber())
                .andExpect(jsonPath("$.contents[0].title").isString())
                .andExpect(jsonPath("$.contents[0].runtime").isNumber())
                .andExpect(jsonPath("$.contents[0].director").isString())
                .andExpect(jsonPath("$.contents[0].actors").isString())
                .andExpect(jsonPath("$.contents[0].plot").isString())
                .andExpect(jsonPath("$.contents[0].posterUrl").isString());
    }

    @Test
    void should_return_201_when_rating_movie_successful() throws Exception {
        mockMvc.perform(post("/movies/1/ratings")
                        .contentType("application/json")
                        .content("""
                                {"score": 5}"""))
                .andExpect(status().isCreated());
    }
}