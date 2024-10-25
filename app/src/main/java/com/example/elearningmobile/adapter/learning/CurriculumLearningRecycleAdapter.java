package com.example.elearningmobile.adapter.learning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.LectureRecycleAdapter;
import com.example.elearningmobile.model.Curriculum;
import com.example.elearningmobile.model.course.CourseLearningVm;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.section.SectionVM;

import java.util.ArrayList;
import java.util.List;

public class CurriculumLearningRecycleAdapter extends RecyclerView.Adapter<CurriculumLearningRecycleAdapter.CurriculumHolder>{


    private List<SectionVM> sectionVMS = new ArrayList<>();

    private Context context ;


    public CurriculumLearningRecycleAdapter(List<SectionVM> sectionVMS, Context context) {
        this.sectionVMS = sectionVMS;
        this.context = context;
    }

    @NonNull
    @Override
    public CurriculumLearningRecycleAdapter.CurriculumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curriculum_learning_item, parent, false);
        return new CurriculumLearningRecycleAdapter.CurriculumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurriculumLearningRecycleAdapter.CurriculumHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_sectionNum_learning.setText(position + 1 + "");
        SectionVM sectionVM = sectionVMS.get(position);
        holder.tv_sectionTitle_learning.setText(sectionVM.getTitle());
        List<Curriculum> curriculumList = sectionVM.getCurriculums();
        LectureLearningRecycleAdapter lectureRecycleAdapter = new LectureLearningRecycleAdapter(curriculumList, sectionVMS, context);
        holder.rc_lectures_learning.setAdapter(lectureRecycleAdapter);
        holder.rc_lectures_learning.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    @Override
    public int getItemCount() {
        return sectionVMS.size();
    }

    class CurriculumHolder extends RecyclerView.ViewHolder{
        TextView tv_sectionNum_learning, tv_sectionTitle_learning;
        RecyclerView rc_lectures_learning;

        public CurriculumHolder(@NonNull View itemView) {
            super(itemView);
            tv_sectionNum_learning = itemView.findViewById(R.id.tv_sectionNum_learning);
            tv_sectionTitle_learning = itemView.findViewById(R.id.tv_sectionTitle_learning);
            rc_lectures_learning = itemView.findViewById(R.id.rc_lecture_learning);
        }
    }
}
