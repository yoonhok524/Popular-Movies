package com.youknow.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.utilities.MovieCrawler;

import java.util.List;

import static com.youknow.popularmovies.utilities.MovieCrawler.MOST_POPULAR;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ProgressBar mProgressBar;
    Spinner mSpnSortOrder;
    GridView mGridMovies;
    ImageAdapter mImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mSpnSortOrder = (Spinner) findViewById(R.id.spn_sort_order);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.movie_sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnSortOrder.setAdapter(adapter);
        mSpnSortOrder.setOnItemSelectedListener(this);

        mGridMovies = (GridView) findViewById(R.id.grid_movies);
        mImageAdapter = new ImageAdapter(this);
        mGridMovies.setAdapter(mImageAdapter);
        mGridMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = mImageAdapter.getMoviesList().get(position);
                Intent intent = new Intent(getBaseContext(), MovieActivity.class);
                intent.putExtra(getString(R.string.movie), movie);
                startActivity(intent);
            }
        });

        new FetchMovieTask().execute(MOST_POPULAR);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        new FetchMovieTask().execute(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class FetchMovieTask extends AsyncTask<Integer, Void, List<Movie>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(Integer... params) {
            int type;
            if (params == null || params.length == 0) {
                type = MOST_POPULAR;
            } else {
                type = params[0];
            }

            return MovieCrawler.getMovies(type);
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            mImageAdapter.setMoviesList(movies);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
