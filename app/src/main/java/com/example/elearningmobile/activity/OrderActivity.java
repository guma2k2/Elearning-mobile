package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CartRecycleAdapter;
import com.example.elearningmobile.adapter.OrderRecycleAdapter;
import com.example.elearningmobile.api.OrderApi;
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.variable.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private List<OrderVM> orders = new ArrayList<>();
    private OrderRecycleAdapter orderRecycleAdapter;

    private RecyclerView rc_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setControl();
        setOrdersByUser();
        setEvent();
    }

    private void setOrdersByUser() {
        GlobalVariable globalVariable = (GlobalVariable) getApplication();
        if (globalVariable.isLoggedIn()) {
            String token = globalVariable.getAccess_token() ;
            OrderApi.orderApi.getOrdersByUser(token).enqueue(new Callback<List<OrderVM>>() {
                @Override
                public void onResponse(Call<List<OrderVM>> call, Response<List<OrderVM>> response) {
                    List<OrderVM> res = response.body();
                    orders.addAll(res);
                }

                @Override
                public void onFailure(Call<List<OrderVM>> call, Throwable t) {
                    Toast.makeText(getApplication(), t.toString(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

    private void setEvent() {
        GlobalVariable globalVariable = (GlobalVariable) getApplication();
        orderRecycleAdapter = new OrderRecycleAdapter(orders, getApplication(), globalVariable);
        rc_order.setAdapter(orderRecycleAdapter);
        rc_order.setLayoutManager(new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false));
    }



    private void setControl() {
        rc_order = findViewById(R.id.rc_order);
        orders = new ArrayList<>();
    }
}