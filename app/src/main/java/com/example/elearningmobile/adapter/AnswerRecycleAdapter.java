package com.example.elearningmobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.LearningActivity;
import com.example.elearningmobile.model.AnswerVM;
import com.example.elearningmobile.model.CourseDetailType;

import java.util.List;

public class AnswerRecycleAdapter extends RecyclerView.Adapter<AnswerRecycleAdapter.AnswerHolder>{

    private List<AnswerVM> answerVMS;

    private Context context ;

    public AnswerRecycleAdapter(List<AnswerVM> answerVMS, Context context) {
        this.answerVMS = answerVMS;
        this.context = context;
    }


    @NonNull
    @Override
    public AnswerRecycleAdapter.AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
        return new AnswerRecycleAdapter.AnswerHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull AnswerRecycleAdapter.AnswerHolder holder, @SuppressLint("RecyclerView") int position) {
        AnswerVM answerVM = answerVMS.get(position);
        holder.rb_answerText.setText(Html.fromHtml(answerVM.getAnswerText()));

        if (context instanceof LearningActivity) {
            holder.rb_answerText.setChecked(position == ((LearningActivity) context).selectedPosition);
            if (((LearningActivity) context).showingAnswer && ((LearningActivity) context).selectedPosition == position) {
                if (answerVM.isCorrect() ) {
                    holder.rb_answerText.setBackgroundColor(Color.GREEN);
                } else {
                    holder.rb_answerText.setBackgroundColor(Color.RED);
                }
            } else {
                holder.rb_answerText.setBackgroundColor(Color.TRANSPARENT);
            }
        }

        holder.rb_answerText.setOnClickListener(v -> {
            ((LearningActivity) context).showingAnswer = false;
            ((LearningActivity) context).selectedPosition = position; // Update selected position
            ((LearningActivity) context).answerRecycleAdapter.notifyDataSetChanged(); // Refresh the RecyclerView to update UI
        });
    }

    @Override
    public int getItemCount() {
        return answerVMS != null ?  answerVMS.size() : 0 ;
    }

    class AnswerHolder extends RecyclerView.ViewHolder{
        RadioButton rb_answerText;

        public AnswerHolder(@NonNull View itemView) {
            super(itemView);
            rb_answerText = itemView.findViewById(R.id.rb_answerText);
        }
    }
}
