package com.youknow.popularmovies.model.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aaron on 02/07/2017.
 */

public class MoviesDBHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = MoviesDBHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 12;

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
                MoviesContract.MovieEntry.TABLE_MOVIES + "("
                + MoviesContract.MovieEntry._ID + " INTEGER PRIMARY KEY, "
                + MoviesContract.MovieEntry.COLUMN_POSTER_URL + " STRING NOT NULL, "
                + MoviesContract.MovieEntry.COLUMN_TITLE + " STRING NOT NULL, "
                + MoviesContract.MovieEntry.COLUMN_POPULARITY + " STRING NOT NULL, "
                + MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE + " STRING NOT NULL, "
                + MoviesContract.MovieEntry.COLUMN_OVERVIEW + " STRING NOT NULL, "
                + MoviesContract.MovieEntry.COLUMN_RELEASE_DATE + " STRING NOT NULL, "
                + MoviesContract.MovieEntry.COLUMN_IS_FAVORITE + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ". OLD DATA WILL BE DESTROYED");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MovieEntry.TABLE_MOVIES);
        sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + MoviesContract.MovieEntry.TABLE_MOVIES + "'");

        onCreate(sqLiteDatabase);
    }
}