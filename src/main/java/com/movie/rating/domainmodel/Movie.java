package com.movie.rating.domainmodel;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    private Integer id;
    private String title;
    private Integer runtime;
    private String director;
    private String actors;
    private String plot;
    @Column(name = "poster_url")
    private String posterUrl;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<Rating> ratings = new HashSet<>();

    @Column(name = "average_rating")
    private Double averageRating;

    public Movie() {
    }

    public Rating addRating(Rating rating) {
        rating.setMovie(this);
        this.ratings.add(rating);
        this.averageRating = ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0d);
        return rating;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;

        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(runtime);
        result = 31 * result + Objects.hashCode(director);
        result = 31 * result + Objects.hashCode(actors);
        result = 31 * result + Objects.hashCode(plot);
        result = 31 * result + Objects.hashCode(posterUrl);
        return result;
    }
}
