package com.movie.rating.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public Rating() {
    }

    public Rating(Integer score) {
        this.score = score;
    }

    public static Rating create(Integer score) {
        if (score < 1 || score > 10) {
            throw new InvalidRatingException(Map.of("score", "Score must be between 1 and 10"));
        }
        return new Rating(score);
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating rating1)) return false;

        return score.equals(rating1.score);
    }

    @Override
    public int hashCode() {
        return score.hashCode();
    }
}
