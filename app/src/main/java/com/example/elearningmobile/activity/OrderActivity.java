package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
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
    private List<OrderVM> data_all = new ArrayList<>();
    private final String pending = "PENDING";
    private final String all = "ALL";
    private final String failure = "FAILURE";
    private final String success = "SUCCESS";
    private String currentStatus = all;


    private TextView tv_order_status_all, tv_order_status_pending, tv_order_status_failure, tv_order_status_success;
    private RecyclerView rc_order;

    private Button btn_order_management_back;

    private SearchView sv_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setControl();
        setEvent();
    }

    private void setOrdersByUser() {
        GlobalVariable globalVariable = (GlobalVariable) getApplication();
        if (globalVariable.isLoggedIn()) {

            String token = globalVariable.getAccess_token();
            String bearerToken = "Bearer " + token;
            if (!currentStatus.equals(all)) {
                OrderApi.orderApi.getOrdersByUserAndStatus(bearerToken, currentStatus).enqueue(new Callback<List<OrderVM>>() {
                    @Override
                    public void onResponse(Call<List<OrderVM>> call, Response<List<OrderVM>> response) {
                        List<OrderVM> res = response.body();
                        if (res.size() > 0) {
                            orders.clear();
                            data_all.clear();
                            orders.addAll(res);
                            data_all.addAll(res);
                            orderRecycleAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<OrderVM>> call, Throwable t) {
                        Toast.makeText(getApplication(), t.toString(), Toast.LENGTH_SHORT);
                    }
                });
            } else {
                OrderApi.orderApi.getOrdersByUser(bearerToken).enqueue(new Callback<List<OrderVM>>() {
                    @Override
                    public void onResponse(Call<List<OrderVM>> call, Response<List<OrderVM>> response) {
                        List<OrderVM> res = response.body();
                        if (res.size() > 0) {
                            orders.clear();
                            data_all.clear();
                            orders.addAll(res);
                            data_all.addAll(res);
                            orderRecycleAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<OrderVM>> call, Throwable t) {
                        Toast.makeText(getApplication(), t.toString(), Toast.LENGTH_SHORT);
                    }
                });
            }
        }
    }

    private void setEvent() {
        tv_order_status_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOrderStatusTextView();
                tv_order_status_all.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.main_color));
                currentStatus = all;
                setOrdersByUser();
            }
        });
        tv_order_status_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOrderStatusTextView();
                tv_order_status_pending.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.main_color));
                currentStatus = pending;
                setOrdersByUser();
            }
        });
        tv_order_status_failure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOrderStatusTextView();
                tv_order_status_failure.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.main_color));
                currentStatus = failure;
                setOrdersByUser();
            }
        });
        tv_order_status_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOrderStatusTextView();
                tv_order_status_success.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.main_color));
                currentStatus = success;
                setOrdersByUser();
            }
        });
        sv_order.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                orders.clear();
                if (newText.equals("")) {
                    orders.addAll(data_all);

                } else {
                    for (OrderVM order : data_all) {
                        if (order.getId().toString().contains(newText)) {
                            orders.add(order);
                        }
                    }
                }
                orderRecycleAdapter.notifyDataSetChanged();
                return false;
            }
        });
        btn_order_management_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToProfile();
            }
        });

        orderRecycleAdapter = new OrderRecycleAdapter(orders, this);
        rc_order.setAdapter(orderRecycleAdapter);
        rc_order.setLayoutManager(new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false));
    }

    private void resetOrderStatusTextView() {

        tv_order_status_all.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        tv_order_status_pending.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        tv_order_status_failure.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        tv_order_status_success.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
    }

    private void redirectToProfile() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("fragment", R.id.nav_profile);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setOrdersByUser();
    }




    private void setControl() {
        rc_order = findViewById(R.id.rc_order);
        sv_order = findViewById(R.id.sv_order);
        tv_order_status_all = findViewById(R.id.tv_order_status_all);
        tv_order_status_pending = findViewById(R.id.tv_order_status_pending);
        tv_order_status_failure= findViewById(R.id.tv_order_status_failure);
        tv_order_status_success = findViewById(R.id.tv_order_status_success);
        btn_order_management_back = findViewById(R.id.btn_order_management_back);
        orders = new ArrayList<>();
    }
}