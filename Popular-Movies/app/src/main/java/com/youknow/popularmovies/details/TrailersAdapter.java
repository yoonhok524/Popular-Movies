package com.youknow.popularmovies.details;

import com.youknow.popularmovies.R;
import com.youknow.popularmovies.model.Trailer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 02/07/2017.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    private static Context mContext;
    private List<Trailer> mTrailers = new ArrayList<>();

    public TrailersAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trailer trailer = mTrailers.get(position);
        holder.mTrailer = trailer;
        holder.tvTrailerName.setText(trailer.getName());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    public void setTrailers(List<Trailer> trailers) {
        mTrailers.clear();
        mTrailers.addAll(trailers);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String YOUTUBE_BASE_URL = "http://www.youtube.com/watch?v=";

        public Trailer mTrailer;
        public ImageView ivPlay;
        public TextView tvTrailerName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPlay = (ImageView) itemView.findViewById(R.id.iv_play);
            tvTrailerName = (TextView) itemView.findViewById(R.id.tv_trailer_name);
            ivPlay.setOnClickListener(this);
            tvTrailerName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(YOUTUBE_BASE_URL + mTrailer.getKey()));
            mContext.startActivity(intent);
        }
    }
}
