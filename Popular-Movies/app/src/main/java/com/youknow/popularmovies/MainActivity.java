package com.youknow.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.youknow.popularmovies.details.MovieActivity;
import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.datasource.MoviesContract;

import java.util.ArrayList;
import java.util.List;

import static com.youknow.popularmovies.utilities.MovieCrawler.MOST_POPULAR;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AsyncTaskCompleteListener {

    private static final int FAVORITE = 2;

    ProgressBar mProgressBar;
    Spinner mSpnSortOrder;
    GridView mGridMovies;
    TextView mTvErrorMessage;
    ImageAdapter mImageAdapter;
    boolean isLoaded = false;

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
        mTvErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);

        mGridMovies = (GridView) findViewById(R.id.grid_movies);
        mImageAdapter = new ImageAdapter(this);
        if (savedInstanceState != null) {
            mImageAdapter.setMoviesList(savedInstanceState.<Movie>getParcelableArrayList(getString(R.string.key_movies)));
            isLoaded = true;
        }

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
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!isLoaded) {
            new FetchMovieTask(this).execute(mSpnSortOrder.getSelectedItemPosition());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.key_movies), (ArrayList<? extends Parcelable>) mImageAdapter.getMoviesList());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        new FetchMovieTask(this).execute(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void enableProgressBar(boolean enable) {
        int visible = enable ? View.VISIBLE : View.INVISIBLE;
        mProgressBar.setVisibility(visible);
    }

    @Override
    public void onLoadedMovies(List<Movie> movies) {
        int visible = (movies == null || movies.isEmpty()) ? View.VISIBLE : View.INVISIBLE;
        mTvErrorMessage.setVisibility(visible);
        mImageAdapter.setMoviesList(movies);
    }

}
