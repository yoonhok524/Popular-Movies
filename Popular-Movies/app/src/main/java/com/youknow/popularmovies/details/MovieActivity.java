package com.youknow.popularmovies.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.youknow.popularmovies.R;
import com.youknow.popularmovies.model.Movie;
import com.youknow.popularmovies.model.Review;
import com.youknow.popularmovies.model.Trailer;

import java.util.List;

public class MovieActivity extends AppCompatActivity implements MovieContract.View {

    private static final String TAG = MovieActivity.class.getSimpleName();

    TextView tvTitle;
    TextView tvReleaseDate;
    TextView tvPlot;
    ImageView ivPoster;
    TextView tvRating;
    ImageView ivFavorite;
    RecyclerView rvTrailers;
    TrailersAdapter trailersAdapter;
    RecyclerView.LayoutManager trailersLayoutManager;

    RecyclerView rvReviews;
    ReviewsAdapter reviewsAdapter;
    RecyclerView.LayoutManager reviewsLayoutManager;

    Movie mMovie;
    boolean isFavorite = false;

    MovieContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mPresenter = new MoviePresenter(this);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvPlot = (TextView) findViewById(R.id.tv_plot);
        ivPoster = (ImageView) findViewById(R.id.iv_poster);
        tvRating = (TextView) findViewById(R.id.tv_rating);
        ivFavorite = (ImageView) findViewById(R.id.iv_favorite);
        rvTrailers = (RecyclerView) findViewById(R.id.rv_trailers);
        rvTrailers.setHasFixedSize(true);
        trailersLayoutManager = new LinearLayoutManager(this);
        trailersLayoutManager.setAutoMeasureEnabled(true);
        rvTrailers.setLayoutManager(trailersLayoutManager);
        trailersAdapter = new TrailersAdapter(this);
        rvTrailers.setAdapter(trailersAdapter);
        rvTrailers.setNestedScrollingEnabled(true);

        rvReviews = (RecyclerView) findViewById(R.id.rv_reviews);
        rvReviews.setHasFixedSize(true);
        reviewsLayoutManager = new LinearLayoutManager(this);
        reviewsLayoutManager.setAutoMeasureEnabled(true);
        rvReviews.setLayoutManager(reviewsLayoutManager);
        reviewsAdapter = new ReviewsAdapter(this);
        rvReviews.setAdapter(reviewsAdapter);
        rvReviews.setNestedScrollingEnabled(true);


        Intent intent = getIntent();
        if (intent.hasExtra(getString(R.string.movie))) {
            mMovie = (Movie) intent.getSerializableExtra(getString(R.string.movie));
            setTitle(mMovie.getTitle());
            tvTitle.setText(mMovie.getTitle());
            tvReleaseDate.setText(mMovie.getReleaseDate());
            tvPlot.setText(mMovie.getOverview());
            tvRating.setText(mMovie.getVoteAverage());
            Picasso.with(this).load(mMovie.getPosterPath()).into(ivPoster);

            isFavorite = mPresenter.isFavorite(mMovie.getId());
            if (isFavorite) {
                ivFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_colored_24px));
            }
            mPresenter.loadTrailers(mMovie.getId());
            mPresenter.loadReviews(mMovie.getId());
        }
    }

    public void onClickFavorite(View view) {
        if (isFavorite) {
            isFavorite = false;
            ivFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_24px));
            mPresenter.disableFavorite(mMovie);
        } else {
            isFavorite = true;
            ivFavorite.setImageDrawable(getDrawable(R.drawable.ic_favorite_colored_24px));
            mPresenter.enableFavorite(mMovie);
        }
    }

    @Override
    public void onLoadedTrailers(List<Trailer> trailers) {
        trailersAdapter.setTrailers(trailers);
    }

    @Override
    public void onloadedReviews(List<Review> reviews) {
        reviewsAdapter.setReviews(reviews);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(ivFavorite, message, Snackbar.LENGTH_LONG).show();
    }
}
