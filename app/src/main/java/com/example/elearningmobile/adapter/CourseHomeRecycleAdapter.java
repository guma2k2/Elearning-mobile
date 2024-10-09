package com.example.elearningmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;

public class CourseHomeRecycleAdapter extends RecyclerView.Adapter<CourseHomeRecycleAdapter.CourseHolder>{
    @NonNull
    @Override
    public CourseHomeRecycleAdapter.CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_home_item, parent, false);
        return new CourseHomeRecycleAdapter.CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHomeRecycleAdapter.CourseHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        ImageView iv_course;
        TextView tv_courseTitle, tv_courseInstructor, tv_coursePrice ;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            tv_courseTitle = itemView.findViewById(R.id.tv_courseTitle);
            tv_courseInstructor = itemView.findViewById(R.id.tv_courseInstructor);
            tv_coursePrice = itemView.findViewById(R.id.tv_coursePrice);
        }
    }
}
