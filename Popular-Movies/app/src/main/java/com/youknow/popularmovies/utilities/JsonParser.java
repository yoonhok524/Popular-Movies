package com.youknow.popularmovies.utilities;

import com.youknow.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 2017-06-04.
 */

public class JsonParser {
    private static final String RESULTS = "results";
    private static final String ID = "id";
    private static final String POSTER_PATH = "poster_path";
    private static final String TITLE = "title";
    private static final String POPULARITY = "popularity";
    private static final String OVERVIEW = "overview";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String RELEASE_DATE = "release_date";

    public static List<Movie> getMovies(String rawMovies) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        if (rawMovies != null && !rawMovies.isEmpty()) {
            JSONObject moviesJson = new JSONObject(rawMovies);
            if (moviesJson.has(RESULTS)) {
                JSONArray movieArray = moviesJson.getJSONArray(RESULTS);
                for (int i = 0; i < movieArray.length(); i++) {
                    JSONObject movieJson = movieArray.getJSONObject(i);
                    String id = movieJson.getString(ID);
                    String posterPath = movieJson.getString(POSTER_PATH);
                    String title = movieJson.getString(TITLE);
                    String popularity = movieJson.getString(POPULARITY);
                    String voteAverage = movieJson.getString(VOTE_AVERAGE);
                    String overview = movieJson.getString(OVERVIEW);
                    String releaseDate = movieJson.getString(RELEASE_DATE);

                    movies.add(new Movie(id, posterPath, title, popularity, voteAverage, overview, releaseDate));
                }
            }
        }

        return movies;
    }
}
