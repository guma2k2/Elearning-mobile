package com.example.elearningmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.LearningActivity;
import com.example.elearningmobile.activity.OrderDetailActivity;
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.learning.LearningCourseVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningRecycleAdapter extends RecyclerView.Adapter<LearningRecycleAdapter.CourseHolder>{

    private List<LearningCourseVM> learningCourseVMS;

    private Context context;

    private static final String progressFormat = "%d complete";


    public LearningRecycleAdapter(List<LearningCourseVM> learningCourseVMS, Context context) {
        this.learningCourseVMS = learningCourseVMS;
        this.context = context;
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

        holder.ll_courseLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LearningActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("slug", courseListGetVM.getSlug());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return learningCourseVMS.size();
    }

    class CourseHolder extends RecyclerView.ViewHolder{
        ImageView iv_course_learning;
        TextView tv_courseTitle_learning, tv_courseInstructor_learning, tv_coursePrice_learning;
        ProgressBar pb_rating_learning;

        LinearLayout ll_courseLearning;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            ll_courseLearning = itemView.findViewById(R.id.ll_courseLearning);
            iv_course_learning = itemView.findViewById(R.id.iv_course_learning);
            tv_courseTitle_learning = itemView.findViewById(R.id.tv_courseTitle_learning);
            tv_courseInstructor_learning = itemView.findViewById(R.id.tv_courseInstructor_learning);
            pb_rating_learning = itemView.findViewById(R.id.pb_rating_learning);
            tv_coursePrice_learning = itemView.findViewById(R.id.tv_coursePrice_learning);
        }
    }
}
