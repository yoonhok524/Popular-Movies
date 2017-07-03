package com.youknow.popularmovies.model.datasource;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Aaron on 02/07/2017.
 */

public class MoviesContract {
    public static final String CONTENT_AUTHORITY = "com.youknow.popularmovies";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MovieEntry implements BaseColumns {
        // table name
        public static final String TABLE_MOVIES = "movie";
        // columns
        public static final String _ID = "_id";
        public static final String COLUMN_IS_FAVORITE = "is_favorite";
        public static final String COLUMN_POSTER_URL = "poster_url";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_VOTE_AVERAGE = "voteAverage";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";

        public static final int FAVORITE = 1;
        public static final int NOT_FAVORITE = 0;

        // create content uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_MOVIES).build();
        public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIES;

        // for building URIs on insertion
        public static Uri buildMoviesUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
