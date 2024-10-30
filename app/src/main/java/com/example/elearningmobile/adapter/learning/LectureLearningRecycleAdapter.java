package com.example.elearningmobile.adapter.learning;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.LearningActivity;
import com.example.elearningmobile.model.Curriculum;
import com.example.elearningmobile.model.LectureVm;
import com.example.elearningmobile.model.course.CourseLearningVm;
import com.example.elearningmobile.model.section.SectionVM;

import java.util.List;

public class LectureLearningRecycleAdapter extends RecyclerView.Adapter<LectureLearningRecycleAdapter.LectureHolder>{


    private List<Curriculum> curriculumList;

    private List<SectionVM> sectionVMS;

    private Context context;


    public LectureLearningRecycleAdapter(List<Curriculum> curriculumList, List<SectionVM> sectionVMS, Context context) {
        this.curriculumList = curriculumList;
        this.sectionVMS = sectionVMS;
        this.context = context;
    }

    @NonNull
    @Override
    public LectureLearningRecycleAdapter.LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecture_learning_item, parent, false);
        return new LectureLearningRecycleAdapter.LectureHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull LectureLearningRecycleAdapter.LectureHolder holder, int position) {
        Curriculum curriculum = curriculumList.get(position);
        holder.tv_lectureNum_learning.setText(getLectureNum(position)+"");
        holder.tv_lectureName_learning.setText(curriculum.getTitle());


        if (context instanceof LearningActivity) {
            if (curriculum.getId() == ((LearningActivity) context).curriculumId &&
                    curriculum.getType().name().equals(((LearningActivity) context).type)) {
                holder.ll_lecture_learning.setBackgroundColor(R.color.activeLecture);
            }
        }
        if (curriculum instanceof LectureVm) {
            holder.tv_lectureDesc_learning.setText(curriculum.getType() + " - " + ((LectureVm) curriculum).getDuration() );
        } else {
            holder.tv_lectureDesc_learning.setText(curriculum.getType()+"");
        }


        holder.ll_lecture_learning.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "NotifyDataSetChanged"})
            @Override
            public void onClick(View v) {
                if (context instanceof LearningActivity) {
                    if (curriculum instanceof  LectureVm) {
                        String url = ((LectureVm) curriculum).getVideoId();
                        ((LearningActivity) context).runVideo(url);
                        ((LearningActivity) context).curriculumId = curriculum.getId();
                        ((LearningActivity) context).type = curriculum.getType().name();
                        ((LearningActivity) context).curriculumLearningRecycleAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
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
        TextView tv_lectureNum_learning, tv_lectureName_learning, tv_lectureDesc_learning;

        LinearLayout ll_lecture_learning;

        public LectureHolder(@NonNull View itemView) {
            super(itemView);
            tv_lectureNum_learning = itemView.findViewById(R.id.tv_lectureNum_learning);
            ll_lecture_learning = itemView.findViewById(R.id.ll_lecture_learning);
            tv_lectureName_learning = itemView.findViewById(R.id.tv_lectureName_learning);
            tv_lectureDesc_learning = itemView.findViewById(R.id.tv_lectureDesc_learning);
        }
    }
}
