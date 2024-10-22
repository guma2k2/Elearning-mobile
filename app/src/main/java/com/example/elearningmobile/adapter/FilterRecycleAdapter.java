package com.example.elearningmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.category.CategoryListGetVM;

import java.util.ArrayList;
import java.util.List;

public class FilterRecycleAdapter extends RecyclerView.Adapter<FilterRecycleAdapter.StringHolder>{

    private Context context;

    private List<CategoryListGetVM> categoryListGetVMList;


    public FilterRecycleAdapter(Context context, List<CategoryListGetVM> categoryListGetVMList) {
        this.context = context;
        this.categoryListGetVMList = categoryListGetVMList;
    }

    @NonNull
    @Override
    public FilterRecycleAdapter.StringHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item, parent, false);
        return new FilterRecycleAdapter.StringHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterRecycleAdapter.StringHolder holder, int position) {
        CategoryListGetVM categoryListGetVM = categoryListGetVMList.get(position);
        holder.tv_categoryName_filter.setText(categoryListGetVM.getName());
        holder.cl_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryListGetVMList.size();
    }

    class StringHolder extends RecyclerView.ViewHolder{
        TextView tv_categoryName_filter;

        ConstraintLayout cl_filter;

        public StringHolder(@NonNull View itemView) {
            super(itemView);
            tv_categoryName_filter = itemView.findViewById(R.id.tv_categoryName_filter);
            cl_filter = itemView.findViewById(R.id.cl_filter);
        }
    }
}
