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
import com.example.elearningmobile.model.section.SectionVM;

import java.util.List;

public class LectureRecycleAdapter extends RecyclerView.Adapter<LectureRecycleAdapter.LectureHolder>{


    private List<Curriculum> curriculumList;

    private List<SectionVM> sectionVMS;

    public LectureRecycleAdapter(List<Curriculum> curriculumList, List<SectionVM> sectionVMS) {
        this.curriculumList = curriculumList;
        this.sectionVMS = sectionVMS;
    }

    @NonNull
    @Override
    public LectureRecycleAdapter.LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_item, parent, false);
        return new LectureRecycleAdapter.LectureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureRecycleAdapter.LectureHolder holder, int position) {
        Curriculum curriculum = curriculumList.get(position);
        holder.tv_lectureNum.setText(getLectureNum(position)+"");
        holder.tv_lectureName.setText(curriculum.getTitle());
        if (curriculum instanceof LectureVm) {
            holder.tv_lectureDesc.setText(curriculum.getType() + " - " + ((LectureVm) curriculum).getDuration() );

        } else {
            holder.tv_lectureDesc.setText(curriculum.getType()+"");
        }
    }

    int getLectureNum(int position) {
        int start = 1;
        for(int i = 0 ; i < sectionVMS.size(); i++) {
            for (int j = 0; j < sectionVMS.get(i).getCurriculums().size() ; j++) {
                if (sectionVMS.get(i).getCurriculums().get(j).equals(curriculumList.get(position))) {
                    return start ;
                }
                start++;
            }
        }
        return 0 ;
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
