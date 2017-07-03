package com.youknow.popularmovies.utilities;

import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.Review;
import com.youknow.popularmovies.model.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.youknow.popularmovies.model.Movie.POSTER_BASE_URL;
import static com.youknow.popularmovies.model.Movie.SIZE_500;

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
    private static final String KEY = "key";
    private static final String NAME = "name";
    private static final String SITE = "site";
    private static final String SIZE = "size";
    private static final String TYPE = "type";
    private static final String AUTHOR = "author";
    private static final String CONTENT = "content";
    private static final String URL = "url";

    public static List<Movie> getMovies(String rawMovies) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        if (rawMovies != null && !rawMovies.isEmpty()) {
            JSONObject moviesJson = new JSONObject(rawMovies);
            if (moviesJson.has(RESULTS)) {
                JSONArray movieArray = moviesJson.getJSONArray(RESULTS);
                for (int i = 0; i < movieArray.length(); i++) {
                    JSONObject movieJson = movieArray.getJSONObject(i);
                    String id = movieJson.getString(ID);
                    String posterPath = POSTER_BASE_URL + SIZE_500 + movieJson.getString(POSTER_PATH);
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

    public static List<Trailer> getTrailers(String rawVideos) throws JSONException {
        List<Trailer> trailers = new ArrayList<>();

        if (rawVideos != null && !rawVideos.isEmpty()) {
            JSONObject trailersJson = new JSONObject(rawVideos);
            if (trailersJson.has(RESULTS)) {
                JSONArray trailerArray = trailersJson.getJSONArray(RESULTS);
                for (int i = 0; i < trailerArray.length(); i++) {
                    JSONObject trailerJson = trailerArray.getJSONObject(i);
                    String id = trailerJson.getString(ID);
                    String key = trailerJson.getString(KEY);
                    String name = trailerJson.getString(NAME);
                    String site = trailerJson.getString(SITE);
                    int size = trailerJson.getInt(SIZE);
                    String type = trailerJson.getString(TYPE);

                    trailers.add(new Trailer(id, key, name, site, size, type));
                }
            }
        }

        return trailers;
    }

    public static List<Review> getReviews(String rawData) throws JSONException {
        List<Review> reviews = new ArrayList<>();

        if (rawData != null && !rawData.isEmpty()) {
            JSONObject trailersJson = new JSONObject(rawData);
            if (trailersJson.has(RESULTS)) {
                JSONArray trailerArray = trailersJson.getJSONArray(RESULTS);
                for (int i = 0; i < trailerArray.length(); i++) {
                    JSONObject trailerJson = trailerArray.getJSONObject(i);
                    String id = trailerJson.getString(ID);
                    String author = trailerJson.getString(AUTHOR);
                    String content = trailerJson.getString(CONTENT);
                    String url = trailerJson.getString(URL);

                    reviews.add(new Review(id, author, content, url));
                }
            }
        }

        return reviews;
    }
}
