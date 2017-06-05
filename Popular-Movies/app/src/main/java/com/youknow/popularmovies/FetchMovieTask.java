package com.youknow.popularmovies;

import android.os.AsyncTask;

import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.utilities.MovieCrawler;

import java.util.List;

import static com.youknow.popularmovies.utilities.MovieCrawler.MOST_POPULAR;

/**
 * Created by owner on 2017-06-05.
 */

public class FetchMovieTask extends AsyncTask<Integer, Void, List<Movie>> {

    AsyncTaskCompleteListener mListener;

    public FetchMovieTask(AsyncTaskCompleteListener listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mListener.enableProgressBar(true);
    }

    @Override
    protected List<Movie> doInBackground(Integer... params) {
        int type;
        if (params == null || params.length == 0) {
            type = MOST_POPULAR;
        } else {
            type = params[0];
        }

        return MovieCrawler.getMovies(type);
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        mListener.onLoadedMovies(movies);
        mListener.enableProgressBar(false);
    }

}