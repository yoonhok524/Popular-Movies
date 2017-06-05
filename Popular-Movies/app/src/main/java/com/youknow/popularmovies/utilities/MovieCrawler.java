package com.youknow.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import com.youknow.popularmovies.model.Movie;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by owner on 2017-06-04.
 */

public class MovieCrawler {
    public static final int MOST_POPULAR = 0;
    public static final int TOP_RATED = 1;

    private static final String API_KEY = "8728476f04abf3f446e9e4068d443b54"; // API key should be taken from the movie site.
    private static final String MOST_POPULAR_URL = "http://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;
    private static final String TOP_RATED_URL = "http://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY;

    private static String getResponseFromHttpUrl(String strUrl) {
        Log.d("TEST", "getResponseFromHttpUrl: " + strUrl);
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
        String url = (type == MOST_POPULAR) ? MOST_POPULAR_URL : TOP_RATED_URL;
        String rawData = getResponseFromHttpUrl(url);

        try {
            return JsonParser.getMovies(rawData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
