package com.example.elearningmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.learning.LearningCourseVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningRecycleAdapter extends RecyclerView.Adapter<LearningRecycleAdapter.CourseHolder>{

    private List<LearningCourseVM> learningCourseVMS;

    private static final String progressFormat = "%d complete";

    public LearningRecycleAdapter(List<LearningCourseVM> learningCourseVMS) {
        this.learningCourseVMS = learningCourseVMS;
    }

    @NonNull
    @Override
    public LearningRecycleAdapter.CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_learning_item, parent, false);
        return new LearningRecycleAdapter.CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningRecycleAdapter.CourseHolder holder, int position) {
        LearningCourseVM learningCourseVM = learningCourseVMS.get(position);
        CourseListGetVM courseListGetVM = learningCourseVM.getCourse();
        Long price = courseListGetVM.getPrice();

        // set data for view holder
        Picasso.get().load(courseListGetVM.getImage()).into(holder.iv_course_learning);
        holder.tv_courseTitle_learning.setText(courseListGetVM.getTitle());
        holder.tv_courseInstructor_learning.setText(courseListGetVM.getCreatedBy());
        holder.pb_rating_learning.setProgress(learningCourseVM.getPercentFinished());
        if (learningCourseVM.getPercentFinished() < 100) {
            holder.tv_coursePrice_learning.setText(String.format(progressFormat, learningCourseVM.getPercentFinished()));
        }else {
            holder.tv_coursePrice_learning.setText("completed");
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        ImageView iv_course_learning;
        TextView tv_courseTitle_learning, tv_courseInstructor_learning, tv_coursePrice_learning;
        ProgressBar pb_rating_learning;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            iv_course_learning = itemView.findViewById(R.id.iv_course_learning);
            tv_courseTitle_learning = itemView.findViewById(R.id.tv_courseTitle_learning);
            tv_courseInstructor_learning = itemView.findViewById(R.id.tv_courseInstructor_learning);
            pb_rating_learning = itemView.findViewById(R.id.pb_rating_learning);
            tv_coursePrice_learning = itemView.findViewById(R.id.tv_coursePrice_learning);
        }
    }
}
