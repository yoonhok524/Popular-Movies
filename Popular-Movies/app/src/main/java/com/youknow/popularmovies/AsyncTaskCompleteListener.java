package com.youknow.popularmovies;

import com.youknow.popularmovies.model.Movie;

import java.util.List;

/**
 * Created by owner on 2017-06-05.
 */

public interface AsyncTaskCompleteListener {
    void enableProgressBar(boolean enable);

    void onLoadedMovies(List<Movie> movies);
}
