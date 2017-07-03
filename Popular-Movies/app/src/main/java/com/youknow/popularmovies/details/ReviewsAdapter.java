package com.youknow.popularmovies.details;

import com.youknow.popularmovies.R;
import com.youknow.popularmovies.model.Review;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 02/07/2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private static Context mContext;
    private List<Review> mReviews = new ArrayList<>();

    public ReviewsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review review = mReviews.get(position);
        holder.mReview = review;
        holder.tvReviewAuthor.setText(review.getAuthor());
        holder.tvReviewContent.setText(review.getContent());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public void setReviews(List<Review> reviews) {
        mReviews.clear();
        mReviews.addAll(reviews);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Review mReview;
        public TextView tvReviewAuthor;
        public TextView tvReviewContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvReviewAuthor = (TextView) itemView.findViewById(R.id.tv_review_author);
            tvReviewContent = (TextView) itemView.findViewById(R.id.tv_review_content);
            tvReviewAuthor.setOnClickListener(this);
            tvReviewContent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mReview.getUrl()));
            mContext.startActivity(intent);
        }
    }
}
