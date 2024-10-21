package com.example.elearningmobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.CourseDetailActivity;
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.Curriculum;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.section.SectionVM;

import java.util.ArrayList;
import java.util.List;

public class CurriculumRecycleAdapter extends RecyclerView.Adapter<CurriculumRecycleAdapter.CurriculumHolder>{


    private CourseVM course ;

    private Context context ;

    public CurriculumRecycleAdapter(CourseVM course, Context context) {
        this.course = course;
        this.context = context;
    }

    @NonNull
    @Override
    public CurriculumRecycleAdapter.CurriculumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curriculum_item, parent, false);
        return new CurriculumRecycleAdapter.CurriculumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurriculumRecycleAdapter.CurriculumHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_sectionNum.setText(position + 1 + "");
        SectionVM sectionVM = course.getSections().get(position);
        holder.tv_sectionTitle.setText(sectionVM.getTitle());
        List<Curriculum> curriculumList = sectionVM.getCurriculums();
        LectureRecycleAdapter lectureRecycleAdapter = new LectureRecycleAdapter(curriculumList, course.getSections());
        holder.rc_lectures.setAdapter(lectureRecycleAdapter);
        holder.rc_lectures.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        if (sectionVM.isToggle()) {
            holder.rc_lectures.setVisibility(View.VISIBLE);
        }else {
            holder.rc_lectures.setVisibility(View.GONE);
        }

        holder.iv_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course.getSections().get(position).setToggle(!sectionVM.isToggle());
                if (context instanceof CourseDetailActivity) {
                    ((CourseDetailActivity) context).setCourse(course);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return course.getSections().size();
    }

    class CurriculumHolder extends RecyclerView.ViewHolder{
        ImageView iv_toggle;
        TextView tv_sectionNum, tv_sectionTitle;
        RecyclerView rc_lectures;

        public CurriculumHolder(@NonNull View itemView) {
            super(itemView);
            iv_toggle = itemView.findViewById(R.id.iv_toggle);
            tv_sectionNum = itemView.findViewById(R.id.tv_sectionNum);
            tv_sectionTitle = itemView.findViewById(R.id.tv_sectionTitle);
            rc_lectures = itemView.findViewById(R.id.rc_lectures);
        }
    }
}
