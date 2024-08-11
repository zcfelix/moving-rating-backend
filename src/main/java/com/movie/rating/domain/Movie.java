package com.movie.rating.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

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

    public Movie() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;

        if (!id.equals(movie.id)) return false;
        if (!Objects.equals(title, movie.title)) return false;
        if (!Objects.equals(runtime, movie.runtime)) return false;
        if (!Objects.equals(director, movie.director)) return false;
        if (!Objects.equals(actors, movie.actors)) return false;
        if (!Objects.equals(plot, movie.plot)) return false;
        return Objects.equals(posterUrl, movie.posterUrl);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (posterUrl != null ? posterUrl.hashCode() : 0);
        return result;
    }
}
