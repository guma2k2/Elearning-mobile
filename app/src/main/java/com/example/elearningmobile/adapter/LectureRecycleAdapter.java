package com.example.elearningmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.Curriculum;
import com.example.elearningmobile.model.ECurriculumType;
import com.example.elearningmobile.model.LectureVm;
import com.example.elearningmobile.model.course.CourseVM;

import java.util.List;

public class LectureRecycleAdapter extends RecyclerView.Adapter<LectureRecycleAdapter.LectureHolder>{


    private List<Curriculum> curriculumList;

    public LectureRecycleAdapter(List<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }

    @NonNull
    @Override
    public LectureRecycleAdapter.LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curriculum_item, parent, false);
        return new LectureRecycleAdapter.LectureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureRecycleAdapter.LectureHolder holder, int position) {
        Curriculum curriculum = curriculumList.get(position);
        holder.tv_lectureNum.setText(1+"");
        holder.tv_lectureName.setText(curriculum.getTitle());

    }

    @Override
    public int getItemCount() {
        return curriculumList.size();
    }

    class LectureHolder extends RecyclerView.ViewHolder{
        TextView tv_lectureNum, tv_lectureName, tv_lectureDesc;

        public LectureHolder(@NonNull View itemView) {
            super(itemView);
            tv_lectureNum = itemView.findViewById(R.id.tv_lectureNum);
            tv_lectureName = itemView.findViewById(R.id.tv_lectureName);
            tv_lectureDesc = itemView.findViewById(R.id.tv_lectureDesc);
        }
    }
}
