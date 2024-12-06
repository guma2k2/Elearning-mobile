package com.example.elearningmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.CourseDetailActivity;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.ultity.DataManager;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseHomeRecycleAdapter extends RecyclerView.Adapter<CourseHomeRecycleAdapter.CourseHolder>{

    private List<CourseListGetVM> courses;

    private Context context;

    public CourseHomeRecycleAdapter(List<CourseListGetVM> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseHomeRecycleAdapter.CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_home_item, parent, false);
        return new CourseHomeRecycleAdapter.CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHomeRecycleAdapter.CourseHolder holder, int position) {
        CourseListGetVM courseListGetVM = courses.get(position);
        Long price = courseListGetVM.getPrice();
        Picasso.get().load(courseListGetVM.getImage()).into(holder.iv_course_home);
        holder.tv_courseTitle_home.setText(courseListGetVM.getTitle());
        holder.tv_courseInstructor_home.setText(courseListGetVM.getCreatedBy());
        holder.tv_courseRating_home.setText(courseListGetVM.getAverageRating()+"");
        holder.tv_coursePrice_home.setText(PriceFormatter.formatPriceInVND(price));
        holder.rb_courseRating_home.setRating((float) courseListGetVM.getAverageRating());
        holder.ll_courseHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               redirectToCourseDetail(courseListGetVM);
            }
        });
    }

    private void redirectToCourseDetail(CourseListGetVM courseListGetVM) {
        Intent intent = new Intent(context, CourseDetailActivity.class);
        DataManager.setCourseId(courseListGetVM.getId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        ImageView iv_course_home;
        TextView tv_courseTitle_home, tv_courseInstructor_home, tv_courseRating_home, tv_coursePrice_home ;

        LinearLayout ll_courseHome;
        RatingBar rb_courseRating_home;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            ll_courseHome = itemView.findViewById(R.id.ll_courseHome);
            iv_course_home = itemView.findViewById(R.id.iv_course_home);
            tv_courseTitle_home = itemView.findViewById(R.id.tv_courseTitle_home);
            tv_courseInstructor_home = itemView.findViewById(R.id.tv_courseInstructor_home);
            tv_courseRating_home = itemView.findViewById(R.id.tv_courseRating_home);
            tv_coursePrice_home = itemView.findViewById(R.id.tv_coursePrice_home);
            rb_courseRating_home = itemView.findViewById(R.id.rb_courseRating_home);
        }
    }
}
