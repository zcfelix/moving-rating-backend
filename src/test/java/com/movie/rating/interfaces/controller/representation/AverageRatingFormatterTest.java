package com.movie.rating.interfaces.controller.representation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AverageRatingFormatterTest {

    @ParameterizedTest
    @CsvSource({
            "3, 3.0",
            "3.0, 3.0",
            "3.45, 3.5",
            "3.44, 3.4",
    })
    void should_format_average_rating_correctly(Double averageRating, String expected) {
        assertEquals(expected, AverageRatingFormatter.formatAverageRating(averageRating));
    }
}