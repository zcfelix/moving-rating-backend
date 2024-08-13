package com.movie.rating.domain.repository;

import com.movie.rating.domain.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
