package com.youknow.popularmovies.details;

import com.youknow.popularmovies.model.Trailer;
import com.youknow.popularmovies.utilities.MovieCrawler;

import android.os.AsyncTask;

import java.util.List;

import static com.youknow.popularmovies.utilities.MovieCrawler.MOST_POPULAR;

/**
 * Created by owner on 2017-06-05.
 */

public class FetchTrailerTask extends AsyncTask<String, Void, List<Trailer>> {

    AsyncTaskCompleteListener mListener;

    public FetchTrailerTask(AsyncTaskCompleteListener listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Trailer> doInBackground(String... params) {
        String movieId = params[0];
        return MovieCrawler.getTrailers(movieId);
    }

    @Override
    protected void onPostExecute(List<Trailer> trailers) {
        mListener.onLoadedTrailers(trailers);
    }

}