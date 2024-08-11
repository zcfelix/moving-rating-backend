package com.movie.rating.interfaces.controller.representation;

import java.util.List;

public record PageRepresentation<T>(List<T> contents, Integer totalSize) {
}
