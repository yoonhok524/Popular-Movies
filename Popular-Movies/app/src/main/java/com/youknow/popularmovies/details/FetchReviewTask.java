package com.youknow.popularmovies.details;

import com.youknow.popularmovies.model.Review;
import com.youknow.popularmovies.model.Trailer;
import com.youknow.popularmovies.utilities.MovieCrawler;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by owner on 2017-06-05.
 */

public class FetchReviewTask extends AsyncTask<String, Void, List<Review>> {

    AsyncTaskCompleteListener mListener;

    public FetchReviewTask(AsyncTaskCompleteListener listener) {
        mListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Review> doInBackground(String... params) {
        String movieId = params[0];
        return MovieCrawler.getReviews(movieId);
    }

    @Override
    protected void onPostExecute(List<Review> reviews) {
        mListener.onLoadedReviews(reviews);
    }

}