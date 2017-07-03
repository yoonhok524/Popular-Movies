package com.youknow.popularmovies.details;

import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.Review;
import com.youknow.popularmovies.model.Trailer;

import java.util.List;

/**
 * Created by owner on 2017-06-05.
 */

public interface AsyncTaskCompleteListener {
    void onLoadedTrailers(List<Trailer> trailers);

    void onLoadedReviews(List<Review> reviews);
}
