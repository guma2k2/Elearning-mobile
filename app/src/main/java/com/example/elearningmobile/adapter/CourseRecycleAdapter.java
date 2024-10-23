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
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseRecycleAdapter extends RecyclerView.Adapter<CourseRecycleAdapter.CourseHolder>{

    private List<CourseListGetVM> courses;

    public CourseRecycleAdapter(List<CourseListGetVM> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseRecycleAdapter.CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        return new CourseRecycleAdapter.CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRecycleAdapter.CourseHolder holder, int position) {
        CourseListGetVM courseListGetVM = courses.get(position);
        Long price = courseListGetVM.getPrice();

        // set data for view holder
        Picasso.get().load(courseListGetVM.getImage()).into(holder.iv_courseImage_course);
        holder.tv_courseTitle_course.setText(courseListGetVM.getTitle());
        holder.tv_courseInstructor_course.setText(courseListGetVM.getCreatedBy());
        holder.tv_courseRating_course.setText(courseListGetVM.getAverageRating()+"");
        holder.tv_coursePrice_course.setText(PriceFormatter.formatPriceInVND(price));
        holder.rb_courseRating_course.setRating((float) courseListGetVM.getAverageRating());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        ImageView iv_courseImage_course;
        TextView tv_courseTitle_course, tv_courseInstructor_course, tv_courseRating_course, tv_coursePrice_course ;

        RatingBar rb_courseRating_course;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            iv_courseImage_course = itemView.findViewById(R.id.iv_courseImage_course);
            tv_courseTitle_course = itemView.findViewById(R.id.tv_courseTitle_course);
            tv_courseInstructor_course = itemView.findViewById(R.id.tv_courseInstructor_course);
            tv_courseRating_course = itemView.findViewById(R.id.tv_courseRating_course);
            tv_coursePrice_course = itemView.findViewById(R.id.tv_coursePrice_course);
            rb_courseRating_course = itemView.findViewById(R.id.rb_courseRating_course);
        }
    }
}
