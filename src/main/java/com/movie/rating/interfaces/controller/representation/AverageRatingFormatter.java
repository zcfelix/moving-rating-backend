package com.movie.rating.interfaces.controller.representation;

import java.text.DecimalFormat;

public class AverageRatingFormatter {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");

    public static String formatAverageRating(Double averageRating) {
        return averageRating != null ? DECIMAL_FORMAT.format(averageRating) : "0.0";
    }
}
