package com.youknow.popularmovies.model;

import java.io.Serializable;

/**
 * Created by owner on 2017-06-04.
 */
public class Movie implements Serializable {

    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String SIZE_92 = "w92";
    public static final String SIZE_154 = "w154";
    public static final String SIZE_185 = "w185";
    public static final String SIZE_342 = "w342";
    public static final String SIZE_500 = "w500";
    public static final String SIZE_780 = "w780";
    public static final String SIZE_ORIGINAL = "original";

    String id;
    String posterPath;
    String title;
    String popularity;
    String voteAverage;
    String overview;
    String releaseDate;

    public Movie(String id, String posterPath, String title, String popularity, String voteAverage, String overview, String releaseDate) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", popularity='" + popularity + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
