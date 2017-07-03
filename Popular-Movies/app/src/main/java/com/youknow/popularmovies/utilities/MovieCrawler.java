package com.youknow.popularmovies.utilities;

import android.net.Uri;

import com.youknow.popularmovies.BuildConfig;
import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.Review;
import com.youknow.popularmovies.model.Trailer;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by owner on 2017-06-04.
 */

public class MovieCrawler {
    public static final int MOST_POPULAR = 0;
    public static final int TOP_RATED = 1;
    public static final int FAVORITE = 2;

    private static final String MOST_POPULAR_URL = "http://api.themoviedb.org/3/movie/popular?api_key=" + BuildConfig.API_KEY;
    private static final String TOP_RATED_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=" + BuildConfig.API_KEY;
    private static final String TRAILERS_URL = "http://api.themoviedb.org/3/movie/%s/videos?api_key=" + BuildConfig.API_KEY;
    private static final String REVIEWS_URL = "http://api.themoviedb.org/3/movie/%s/reviews?api_key=" + BuildConfig.API_KEY;

    private static String getResponseFromHttpUrl(String strUrl) {
        Uri uri = Uri.parse(strUrl);
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (url == null) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Movie> getMovies(int type) {
        if (type == FAVORITE) {

            return null;
        }

        String url = (type == MOST_POPULAR) ? MOST_POPULAR_URL : TOP_RATED_URL;
        String rawData = getResponseFromHttpUrl(url);

        try {
            return JsonParser.getMovies(rawData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Trailer> getTrailers(String movieId) {
        String url = String.format(TRAILERS_URL, movieId);
        String rawData = getResponseFromHttpUrl(url);

        try {
            return JsonParser.getTrailers(rawData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Review> getReviews(String movieId) {
        String url = String.format(REVIEWS_URL, movieId);
        String rawData = getResponseFromHttpUrl(url);

        try {
            return JsonParser.getReviews(rawData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
