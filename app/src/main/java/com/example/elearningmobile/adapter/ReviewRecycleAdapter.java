package com.example.elearningmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.review.ReviewVM;

import java.util.ArrayList;
import java.util.List;

public class ReviewRecycleAdapter extends RecyclerView.Adapter<ReviewRecycleAdapter.ReviewHolder>{


    private List<ReviewVM> reviews = new ArrayList<>();

    public ReviewRecycleAdapter(List<ReviewVM> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewRecycleAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewRecycleAdapter.ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewRecycleAdapter.ReviewHolder holder, int position) {
        ReviewVM review = reviews.get(position);
        holder.tv_studentName_review.setText(review.getStudent().getFirstName() + review.getStudent().getLastName());
        holder.tv_reviewCreatedAt_review.setText(review.getCreatedAt());
        holder.tv_reviewText_review.setText(review.getContent());
        holder.rb_rating_review.setRating(review.getRatingStar());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
        RatingBar rb_rating_review;
        TextView tv_studentName_review, tv_reviewCreatedAt_review, tv_reviewText_review;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            rb_rating_review = itemView.findViewById(R.id.rb_rating_review);
            tv_studentName_review = itemView.findViewById(R.id.tv_studentName_review);
            tv_reviewCreatedAt_review = itemView.findViewById(R.id.tv_reviewCreatedAt_review);
            tv_reviewText_review = itemView.findViewById(R.id.tv_reviewText_review);
        }
    }
}
