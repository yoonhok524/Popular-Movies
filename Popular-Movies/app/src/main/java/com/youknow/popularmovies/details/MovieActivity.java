package com.youknow.popularmovies.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youknow.popularmovies.R;
import com.youknow.popularmovies.model.Movie;

public class MovieActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvReleaseDate;
    TextView tvPlot;
    ImageView ivPoster;
    TextView tv_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvPlot = (TextView) findViewById(R.id.tv_plot);
        ivPoster = (ImageView) findViewById(R.id.iv_poster);
        tv_rating = (TextView) findViewById(R.id.tv_rating);

        Intent intent = getIntent();
        if (intent.hasExtra(getString(R.string.movie))) {
            Movie movie = (Movie) intent.getSerializableExtra(getString(R.string.movie));
            setTitle(movie.getTitle());
            tvTitle.setText(movie.getTitle());
            tvReleaseDate.setText(movie.getReleaseDate());
            tvPlot.setText(movie.getOverview());
            tv_rating.setText(movie.getVoteAverage());
            Picasso.with(this).load(movie.getPosterPath()).into(ivPoster);
        }
    }
}
