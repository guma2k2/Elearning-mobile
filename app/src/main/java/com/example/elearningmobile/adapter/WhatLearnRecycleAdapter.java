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
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WhatLearnRecycleAdapter extends RecyclerView.Adapter<WhatLearnRecycleAdapter.ObjectiveHolder>{

    private List<CartListGetVM> carts;

    public WhatLearnRecycleAdapter(List<CartListGetVM> cartListGetVMS) {
        this.carts = cartListGetVMS;
    }

    @NonNull
    @Override
    public WhatLearnRecycleAdapter.ObjectiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new WhatLearnRecycleAdapter.ObjectiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatLearnRecycleAdapter.ObjectiveHolder holder, int position) {
        CartListGetVM cartListGetVM = carts.get(position);
        CourseListGetVM courseListGetVM = cartListGetVM.getCourse();
        Long price = courseListGetVM.getPrice();

        // set data for view holder
        Picasso.get().load(courseListGetVM.getImage()).into(holder.iv_course_cart);
        holder.tv_courseTitle_cart.setText(courseListGetVM.getTitle());
        holder.tv_courseInstructor_cart.setText(courseListGetVM.getCreatedBy());
        holder.tv_courseRating_cart.setText(courseListGetVM.getAverageRating()+"");
        holder.tv_coursePrice_cart.setText(PriceFormatter.formatPriceInVND(price));
        holder.rb_courseRating_cart.setRating((float) courseListGetVM.getAverageRating());

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class ObjectiveHolder extends RecyclerView.ViewHolder{
        ImageView iv_course_cart;
        TextView tv_courseTitle_cart, tv_courseInstructor_cart, tv_courseRating_cart, tv_coursePrice_cart ;

        RatingBar rb_courseRating_cart;
        public ObjectiveHolder(@NonNull View itemView) {
            super(itemView);
            iv_course_cart = itemView.findViewById(R.id.iv_course_cart);
            tv_courseTitle_cart = itemView.findViewById(R.id.tv_courseTitle_cart);
            tv_courseInstructor_cart = itemView.findViewById(R.id.tv_courseInstructor_cart);
            tv_courseRating_cart = itemView.findViewById(R.id.tv_courseRating_cart);
            tv_coursePrice_cart = itemView.findViewById(R.id.tv_coursePrice_cart);
            rb_courseRating_cart = itemView.findViewById(R.id.rb_courseRating_cart);
        }
    }
}
