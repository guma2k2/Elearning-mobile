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
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StringRecycleAdapter extends RecyclerView.Adapter<StringRecycleAdapter.StringHolder>{

    private String[] values;

    private CourseDetailType type;

    public StringRecycleAdapter(String[] values, CourseDetailType type) {
        this.values = values;
        this.type = type;
    }

    @NonNull
    @Override
    public StringRecycleAdapter.StringHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_item, parent, false);
        return new StringRecycleAdapter.StringHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StringRecycleAdapter.StringHolder holder, int position) {
        holder.tv_value_courseDetail.setText(values[position]);
    }

    @Override
    public int getItemCount() {
        return values != null ?  values.length : 0 ;
    }

    class StringHolder extends RecyclerView.ViewHolder{
        ImageView iv_courseDetail;
        TextView tv_value_courseDetail;

        public StringHolder(@NonNull View itemView) {
            super(itemView);
            iv_courseDetail = itemView.findViewById(R.id.iv_courseDetail);
            tv_value_courseDetail = itemView.findViewById(R.id.tv_value_courseDetail);
        }
    }
}
