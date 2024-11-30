package com.example.elearningmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.OrderDetailActivity;
import com.example.elearningmobile.model.order.OrderDetailVM;
import com.example.elearningmobile.model.order.OrderStatus;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.example.elearningmobile.variable.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

public class OrderRecycleAdapter extends RecyclerView.Adapter<OrderRecycleAdapter.OrderHolder> {
    private List<OrderVM> orders = new ArrayList<>();
    private Context context ;

    public OrderRecycleAdapter(List<OrderVM> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {


        OrderVM order = orders.get(position);
        holder.tv_order_id.setText(order.getId() + "");
//        holder.tv_order_address.setText(order.getReceiverAddress());
        holder.tv_order_createdAt.setText(order.getCreatedAt());
//        holder.tv_order_status.setText(translateStatus(order.getStatus()));
        Long totalPrice = getTotalPriceByOrder(order);
        String totalPriceString = PriceFormatter.formatPriceInVND(totalPrice);
        holder.tv_order_totalPrice.setText(totalPriceString);
        holder.tv_order_status.setText(order.getStatus());
        holder.btn_order_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToOrderDetailPage(order);
            }
        });

    }

    private String translateStatus (OrderStatus orderStatus) {
//        switch(orderStatus) {
//            case Pending:
//                return "CHỜ THANH TOÁN";
//            case Shipping:
//                return "ĐANG VẬN CHUYỂN";
//            case Success:
//                return "THÀNH CÔNG";
//            default:
//                break;
//        }
        return "";
    }

    private void deleteOrder(Long orderId) {
//        OrderDataSource orderDataSource = new OrderDataSource(context);
//        orderDataSource.open();
//        OrderDetailDatasource orderDetailDatasource = new OrderDetailDatasource(context);
//        orderDetailDatasource.open();
//
//        int deleteOrderStatus = orderDetailDatasource.deleteByOrder(orderId);
//        if (deleteOrderStatus != -1) {
//            int rowAffected = orderDataSource.deleteOrder(orderId);
//            if (rowAffected != -1) {
//                showSuccessMessage();
//            }else {
//                showErrorMessage();
//            }
//        }else {
//            showErrorMessage();
//        }

    }

    private void showSuccessMessage() {
//        PopupDialog.getInstance(context)
//                .statusDialogBuilder()
//                .createSuccessDialog()
//                .setHeading("Thành công")
//                .setDescription("Bạn đã xóa thành công")
//                .build(dialog -> {
//                    if (context instanceof OrderManagementActivity) {
//                        ((OrderManagementActivity) context).readDb();
//                    }
//                    dialog.dismiss();
//                })
//                .show();
    }

    private void showErrorMessage() {
//        PopupDialog.getInstance(context)
//                .statusDialogBuilder()
//                .createErrorDialog()
//                .setHeading("Thất bại")
//                .setDescription("Có lỗi xảy ra trong quá trình xóa")
//                .build(dialog -> {
//                    dialog.dismiss();
//                })
//                .show();
    }

    private void redirectToOrderDetailPage(OrderVM order) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", order);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }





    private Long getTotalPriceByOrder(OrderVM order) {
        if (order == null || order.getOrderDetails() == null) {
            return 0L;
        }

        Long totalPrice = 0L;
        for (OrderDetailVM orderDetail : order.getOrderDetails()) {
            if (orderDetail != null && orderDetail.getPrice() != null) {
                totalPrice += orderDetail.getPrice();
            }
        }
        return totalPrice;
    }

    private void showConfirmDialog (Long orderId) {
//        PopupDialog.getInstance(context)
//                .standardDialogBuilder()
//                .createIOSDialog()
//                .setHeading("Lưu ý")
//                .setDescription("Bạn có chắc chắn muốn thực hiện không?")
//                .build(new StandardDialogActionListener() {
//                    @Override
//                    public void onPositiveButtonClicked(Dialog dialog) {
//                        deleteOrder(orderId);
//                        dialog.dismiss();
//                    }
//
//                    @Override
//                    public void onNegativeButtonClicked(Dialog dialog) {
//                        dialog.dismiss();
//                    }
//                })
//                .show();
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder{
        TextView tv_order_id, tv_order_createdAt, tv_order_status, tv_order_totalPrice;
        Button btn_order_view, btn_order_edit, btn_order_delete;
        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            tv_order_id = itemView.findViewById(R.id.tv_order_id);
            tv_order_createdAt = itemView.findViewById(R.id.tv_order_createdAt);
            tv_order_status = itemView.findViewById(R.id.tv_order_status);
            tv_order_totalPrice = itemView.findViewById(R.id.tv_order_totalPrice);
            btn_order_view = itemView.findViewById(R.id.btn_order_view);
        }
    }
}
