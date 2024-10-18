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
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CourseHomeRecycleAdapter extends RecyclerView.Adapter<CourseHomeRecycleAdapter.CourseHolder>{

    private List<CourseListGetVM> courses;

    public CourseHomeRecycleAdapter(List<CourseListGetVM> courseListGetVMS) {
        this.courses = courseListGetVMS;
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

        // set data for view holder
        Picasso.get().load(courseListGetVM.getImage()).into(holder.iv_course_home);
        holder.tv_courseTitle_home.setText(courseListGetVM.getTitle());
        holder.tv_courseInstructor_home.setText(courseListGetVM.getCreatedBy());
        holder.tv_courseRating_home.setText(courseListGetVM.getAverageRating()+"");
        holder.tv_coursePrice_home.setText(PriceFormatter.formatPriceInVND(price));
        holder.rb_courseRating_home.setRating((float) courseListGetVM.getAverageRating());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        ImageView iv_course_home;
        TextView tv_courseTitle_home, tv_courseInstructor_home, tv_courseRating_home, tv_coursePrice_home ;

        RatingBar rb_courseRating_home;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            iv_course_home = itemView.findViewById(R.id.iv_course_home);
            tv_courseTitle_home = itemView.findViewById(R.id.tv_courseTitle_home);
            tv_courseInstructor_home = itemView.findViewById(R.id.tv_courseInstructor_home);
            tv_courseRating_home = itemView.findViewById(R.id.tv_courseRating_home);
            tv_coursePrice_home = itemView.findViewById(R.id.tv_coursePrice_home);
            rb_courseRating_home = itemView.findViewById(R.id.rb_courseRating_home);
        }
    }
}
