package com.example.elearningmobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.activity.MainActivity;
import com.example.elearningmobile.activity.OrderActivity;
import com.example.elearningmobile.activity.OrderDetailActivity;
import com.example.elearningmobile.variable.GlobalVariable;


public class ProfileFragment extends Fragment {

    private Context context;

    private TextView tvHoVaTen, tvNgaySinh, tvGioiTinh;

    private Button btnDoiMK, btn_my_order, logout_btn;
    public ProfileFragment(Context context) {
        this.context = context;
    }
    public ProfileFragment() {
        // Required empty public constructor
    }

    private GlobalVariable globalVariable;

    public ProfileFragment(Context context, GlobalVariable globalVariable) {
        this.context = context;
        this.globalVariable = globalVariable;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setControl(view);
        setEvent();
        return view;
    }

    private void redirectToOrder() {
        Intent activityChangeIntent = new Intent(context, OrderActivity.class);
        startActivity(activityChangeIntent);
    }

    private void setEvent() {
        btn_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToOrder();
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalVariable.logOut();
            }
        });
    }

    private void setControl(View view) {
        tvHoVaTen = view.findViewById(R.id.tvHoVaTen);
        tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
        btnDoiMK = view.findViewById(R.id.btnDoiMK);

        btn_my_order = view.findViewById(R.id.btn_my_order);

        logout_btn = view.findViewById(R.id.logout_btn);
    }
}