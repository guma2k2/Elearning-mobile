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
import com.example.elearningmobile.model.category.CategoryListGetVM;
import com.example.elearningmobile.model.category.CategoryVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryHomeRecycleAdapter extends RecyclerView.Adapter<CategoryHomeRecycleAdapter.CategoryHolder>{

    private List<CategoryListGetVM> categories;

    public CategoryHomeRecycleAdapter(List<CategoryListGetVM> categoryListGetVMS) {
        this.categories = categoryListGetVMS;
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

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{
        TextView tv_categoryName_item;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tv_categoryName_item = itemView.findViewById(R.id.tv_categoryName_item);
        }
    }
}
