package com.youknow.popularmovies.details;

import com.youknow.popularmovies.R;
import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.Review;
import com.youknow.popularmovies.model.Trailer;
import com.youknow.popularmovies.model.datasource.MoviesContract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Aaron on 02/07/2017.
 */

public class MoviePresenter implements MovieContract.Presenter, AsyncTaskCompleteListener {

    Context mContext;
    MovieContract.View mView;

    public MoviePresenter(MovieContract.View view) {
        mContext = (Context) view;
        mView = view;
    }

    @Override
    public void loadTrailers(String movieId) {
        new FetchTrailerTask(this).execute(movieId);
    }

    @Override
    public void loadReviews(String movieId) {
        new FetchReviewTask(this).execute(movieId);
    }

    @Override
    public boolean isFavorite(String movieId) {
        Cursor c = mContext.getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,
                new String[]{MoviesContract.MovieEntry._ID, MoviesContract.MovieEntry.COLUMN_IS_FAVORITE},
                MoviesContract.MovieEntry._ID + "=? AND " + MoviesContract.MovieEntry.COLUMN_IS_FAVORITE + "=?",
                new String[]{String.valueOf(movieId), String.valueOf(MoviesContract.MovieEntry.FAVORITE)},
                null);

        return (c.getCount() == 1) ? true : false;
    }

    @Override
    public void enableFavorite(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.MovieEntry._ID, movie.getId());
        values.put(MoviesContract.MovieEntry.COLUMN_IS_FAVORITE, MoviesContract.MovieEntry.FAVORITE);
        values.put(MoviesContract.MovieEntry.COLUMN_POSTER_URL, movie.getPosterPath());
        values.put(MoviesContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MoviesContract.MovieEntry.COLUMN_POPULARITY, movie.getPopularity());
        values.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        values.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());

        mContext.getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI, values);
        mView.showMessage(mContext.getString(R.string.register_favorite, movie.getTitle()));
    }

    @Override
    public void disableFavorite(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(MoviesContract.MovieEntry._ID, movie.getId());
        values.put(MoviesContract.MovieEntry.COLUMN_IS_FAVORITE, MoviesContract.MovieEntry.NOT_FAVORITE);
        values.put(MoviesContract.MovieEntry.COLUMN_POSTER_URL, movie.getPosterPath());
        values.put(MoviesContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MoviesContract.MovieEntry.COLUMN_POPULARITY, movie.getPopularity());
        values.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        values.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        mContext.getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI, values);
        mView.showMessage(mContext.getString(R.string.unregister_favorite, movie.getTitle()));
    }

    @Override
    public void onLoadedTrailers(List<Trailer> trailers) {
        mView.onLoadedTrailers(trailers);
    }

    @Override
    public void onLoadedReviews(List<Review> reviews) {
        mView.onloadedReviews(reviews);
    }
}
