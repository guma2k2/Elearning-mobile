package com.example.elearningmobile.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.OrderDetailRecycleAdapter;
import com.example.elearningmobile.model.order.OrderDetailVM;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.ultity.PriceFormatter;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView tv_orderDetail_order_id, tv_orderDetail_order_createdAt, tv_orderDetail_order_status,

    tv_order_detail_order_totalPrice;
    private RecyclerView rc_order_detail;
    private Button btn_back_to_order_management;

    private OrderDetailRecycleAdapter orderDetailRecycleAdapter;

    private OrderVM order = null;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        setControl();
        setEvent();
    }

    private void setEvent() {
        if (order != null) {
            tv_orderDetail_order_id.setText(order.getId() + "");
            tv_orderDetail_order_createdAt.setText(order.getCreatedAt());
            tv_orderDetail_order_status.setText(convertOrderStatus(order.getStatus()));
            tv_order_detail_order_totalPrice.setText(PriceFormatter.formatPriceInVND(order.getTotalPrice()));
            List<OrderDetailVM> orderDetails = order.getOrderDetails();
            orderDetailRecycleAdapter = new OrderDetailRecycleAdapter(orderDetails);
            rc_order_detail.setAdapter(orderDetailRecycleAdapter);
            rc_order_detail.setLayoutManager(new GridLayoutManager(this, 1));
        }


        btn_back_to_order_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }



    private String convertOrderStatus (String status) {
        if (status.equals("PENDING")) {
            return "ĐANG TIẾN HÀNH";
        } else if (status.equals("SUCCESS")) {
            return "THÀNH CÔNG";
        } else if (status.equals("FAILURE")){
            return "THẤT BẠI";
        }
        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void setControl() {
        tv_orderDetail_order_id= findViewById(R.id.tv_orderDetail_order_id);
        tv_orderDetail_order_createdAt= findViewById(R.id.tv_orderDetail_order_createdAt);
        tv_orderDetail_order_status= findViewById(R.id.tv_orderDetail_order_status);
        rc_order_detail= findViewById(R.id.rc_order_detail);
        tv_order_detail_order_totalPrice = findViewById(R.id.tv_order_detail_order_totalPrice);
        btn_back_to_order_management = findViewById(R.id.btn_back_to_order_management);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            order = extras.getSerializable("order", OrderVM.class);
        }
    }
}