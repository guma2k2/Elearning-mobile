package com.example.elearningmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.CourseByCategoryActivity;
import com.example.elearningmobile.activity.OrderDetailActivity;
import com.example.elearningmobile.model.category.CategoryListGetVM;
import com.example.elearningmobile.model.category.CategoryVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryHomeRecycleAdapter extends RecyclerView.Adapter<CategoryHomeRecycleAdapter.CategoryHolder>{

    private List<CategoryListGetVM> categories;

    private Context context;

    public CategoryHomeRecycleAdapter(List<CategoryListGetVM> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryHomeRecycleAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_home_item, parent, false);
        return new CategoryHomeRecycleAdapter.CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeRecycleAdapter.CategoryHolder holder, int position) {
        CategoryListGetVM categoryVM = categories.get(position);
        holder.tv_categoryName_item.setText(categoryVM.getName());

        holder.ll_category_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToCourseByCategoryPage(categoryVM);
            }
        });

    }

    private void redirectToCourseByCategoryPage(CategoryListGetVM categoryListGetVM) {
        Intent intent = new Intent(context, CourseByCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId", categoryListGetVM.getId());
        bundle.putString("categoryName", categoryListGetVM.getName());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{
        TextView tv_categoryName_item;
        LinearLayout ll_category_home;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            ll_category_home = itemView.findViewById(R.id.ll_category_home);
            tv_categoryName_item = itemView.findViewById(R.id.tv_categoryName_item);
        }
    }
}
