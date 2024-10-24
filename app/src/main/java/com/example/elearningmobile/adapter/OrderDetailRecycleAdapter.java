package com.example.elearningmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.model.course.CourseGetVM;
import com.example.elearningmobile.model.order.OrderDetailVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailRecycleAdapter extends RecyclerView.Adapter<OrderDetailRecycleAdapter.OrderDetailHolder>{
    private List<OrderDetailVM> orderDetails;

    private final String quantityFormat = "x    %d";

    private final String quantityProductFormat = "Tổng số tiền (%d sản phẩm):";

    public OrderDetailRecycleAdapter(List<OrderDetailVM> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @NonNull
    @Override
    public OrderDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item, parent, false);
        return new OrderDetailRecycleAdapter.OrderDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailHolder holder, int position) {
        OrderDetailVM orderDetail = orderDetails.get(position);
        CourseGetVM courseGetVM = orderDetail.getCourse();
        Picasso.get().load(courseGetVM.getImage()).into(holder.iv_courseImage_orderDetail);


        holder.tv_courseTitle_orderDetail.setText(courseGetVM.getTitle());
        holder.tv_coursePrice_orderDetail.setText(PriceFormatter.formatPriceInVND(orderDetail.getPrice()));
        holder.tv_courseTotalPrice_orderDetail.setText(PriceFormatter.formatPriceInVND(orderDetail.getPrice()));
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }

    class OrderDetailHolder extends RecyclerView.ViewHolder{

        ImageView iv_courseImage_orderDetail;
        TextView tv_courseTitle_orderDetail, tv_coursePrice_orderDetail, tv_courseTotalPrice_orderDetail;

        public OrderDetailHolder(@NonNull View itemView) {
            super(itemView);
            iv_courseImage_orderDetail = itemView.findViewById(R.id.iv_courseImage_orderDetail);
            tv_courseTitle_orderDetail = itemView.findViewById(R.id.tv_courseTitle_orderDetail);
            tv_coursePrice_orderDetail = itemView.findViewById(R.id.tv_coursePrice_orderDetail);
            tv_courseTotalPrice_orderDetail = itemView.findViewById(R.id.tv_courseTotalPrice_orderDetail);
        }
    }
}
