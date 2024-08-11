package com.movie.rating.interfaces.controller.representation;

import com.movie.rating.domain.Movie;

public class MovieRepresentation {
    private Integer id;

    private String title;

    private Integer runtime;
    private String director;
    private String actors;
    private String plot;
    private String posterUrl;

    public MovieRepresentation() {
    }

    public MovieRepresentation(Integer id, String title, Integer runtime, String director,
                               String actors, String plot, String posterUrl) {
        this.id = id;
        this.title = title;
        this.runtime = runtime;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.posterUrl = posterUrl;
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

    public static MovieRepresentation from(Movie movie) {
        return new MovieRepresentation(movie.getId(), movie.getTitle(), movie.getRuntime(),
                movie.getDirector(), movie.getActors(), movie.getPlot(), movie.getPosterUrl());
    }
}
