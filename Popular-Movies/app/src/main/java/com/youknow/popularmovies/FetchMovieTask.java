package com.youknow.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.datasource.MoviesContract;
import com.youknow.popularmovies.utilities.MovieCrawler;

import java.util.ArrayList;
import java.util.List;

import static com.youknow.popularmovies.utilities.MovieCrawler.FAVORITE;
import static com.youknow.popularmovies.utilities.MovieCrawler.MOST_POPULAR;

/**
 * Created by owner on 2017-06-05.
 */

public class FetchMovieTask extends AsyncTask<Integer, Void, List<Movie>> {

    Context mContext;
    AsyncTaskCompleteListener mListener;

    public FetchMovieTask(AsyncTaskCompleteListener listener) {
        mContext = (Context) listener;
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

        if (type == FAVORITE) {
            List<Movie> favoriteMovies = new ArrayList<>();
            Cursor c = mContext.getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI, null, MoviesContract.MovieEntry.COLUMN_IS_FAVORITE + "=?", new String[]{String.valueOf(MoviesContract.MovieEntry.FAVORITE)}, null);
            if (c.getCount() == 0) {
                return favoriteMovies;
            }

            c.moveToFirst();
            do {
                String id = c.getString(c.getColumnIndex(MoviesContract.MovieEntry._ID));
                String posterPath = c.getString(c.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POSTER_URL));
                String title = c.getString(c.getColumnIndex(MoviesContract.MovieEntry.COLUMN_TITLE));
                String popularity = c.getString(c.getColumnIndex(MoviesContract.MovieEntry.COLUMN_POPULARITY));
                String voteAverage = c.getString(c.getColumnIndex(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE));
                String overview = c.getString(c.getColumnIndex(MoviesContract.MovieEntry.COLUMN_OVERVIEW));
                String releaseDate = c.getString(c.getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE));

                Movie movie = new Movie(id, posterPath, title, popularity, voteAverage, overview, releaseDate);
                favoriteMovies.add(movie);
            } while (c.moveToNext());

            return favoriteMovies;
        } else {
            return MovieCrawler.getMovies(type);
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        mListener.onLoadedMovies(movies);
        mListener.enableProgressBar(false);
    }

}