package com.youknow.popularmovies.details;

import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.Review;
import com.youknow.popularmovies.model.Trailer;

import java.util.List;

/**
 * Created by Aaron on 02/07/2017.
 */

public interface MovieContract {

    interface View {

        void onLoadedTrailers(List<Trailer> trailers);

        void onloadedReviews(List<Review> reviews);

        void showMessage(String message);
    }

    interface Presenter {

        void loadTrailers(String movieId);

        void loadReviews(String movieId);

        boolean isFavorite(String movieId);

        void enableFavorite(Movie movie);

        void disableFavorite(Movie movie);

    }
}
