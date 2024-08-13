package com.movie.rating.domain.service;

import com.movie.rating.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
