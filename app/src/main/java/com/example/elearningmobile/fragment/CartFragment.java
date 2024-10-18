package com.example.elearningmobile.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CartRecycleAdapter;
import com.example.elearningmobile.adapter.CourseHomeRecycleAdapter;
import com.example.elearningmobile.api.CartApi;
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.variable.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {

    private Context context;
    private GlobalVariable globalVariable ;

    private CartRecycleAdapter cartRecycleAdapter;
    private RecyclerView rc_cart;

    private List<CartListGetVM> carts;
    public CartFragment() {
    }

    public CartFragment(Context context, GlobalVariable globalVariable) {
        this.context = context;
        this.globalVariable = globalVariable;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        setControl(view);
        setEvent();
        return view;
    }

    private void setCarts() {
        if (globalVariable.isLoggedIn()){
            String token = globalVariable.getAccess_token();
            String bearerToken = "Bearer " + token;
            CartApi.cartApi.getCarts(bearerToken).enqueue(new Callback<List<CartListGetVM>>() {
                @Override
                public void onResponse(Call<List<CartListGetVM>> call, Response<List<CartListGetVM>> response) {
                    List<CartListGetVM> res = response.body();
                    carts.addAll(res);
                    cartRecycleAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<CartListGetVM>> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

    private void setEvent() {
        cartRecycleAdapter = new CartRecycleAdapter(carts);
        rc_cart.setAdapter(cartRecycleAdapter);
        rc_cart.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        setCarts();
    }

    private void setControl(View view) {
        rc_cart = view.findViewById(R.id.rc_cart);
        carts = new ArrayList<>();
    }


}