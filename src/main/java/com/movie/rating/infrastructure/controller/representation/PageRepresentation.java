package com.movie.rating.infrastructure.controller.representation;

import java.util.List;

public record PageRepresentation<T>(List<T> contents, Long totalSize) {
}
