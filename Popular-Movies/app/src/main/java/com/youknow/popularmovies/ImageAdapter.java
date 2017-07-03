package com.youknow.popularmovies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youknow.popularmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 2017-06-04.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Movie> mMoviesList = new ArrayList<>();

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mMoviesList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        String posterUrl = mMoviesList.get(pos).getPosterPath();

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.movie_item, null);
        }

        ImageView imgView = (ImageView) convertView.findViewById(R.id.movie_poster);
        Picasso.with(mContext).load(posterUrl).into(imgView);
        return convertView;
    }

    public void setMoviesList(List<Movie> mMoviesList) {
        this.mMoviesList = mMoviesList;
        notifyDataSetChanged();
    }

    public List<Movie> getMoviesList() {
        return mMoviesList;
    }
}
