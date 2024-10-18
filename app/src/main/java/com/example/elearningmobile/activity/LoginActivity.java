package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.api.AuthApi;
import com.example.elearningmobile.model.AuthenticationPostVm;
import com.example.elearningmobile.model.AuthenticationResponse;
import com.example.elearningmobile.model.AuthenticationVm;
import com.example.elearningmobile.variable.GlobalVariable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText ed_email_login, ed_password_login;
    Button btn_login, btn_register_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btn_register_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToRegisterPage();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email_login.getText().toString().trim();
                String password = ed_password_login.getText().toString().trim();
                AuthenticationPostVm authenticationPostVm = new AuthenticationPostVm(email, password);
                AuthApi.authApi.login(authenticationPostVm).enqueue(new Callback<AuthenticationVm>() {
                    @Override
                    public void onResponse(Call<AuthenticationVm> call, Response<AuthenticationVm> response) {
                        AuthenticationVm authenticationResponse = response.body();
                        GlobalVariable globalVariable = (GlobalVariable) getApplication();
                        globalVariable.setAuthentication(authenticationResponse);
                        globalVariable.setLoggedIn(true);
                        Toast.makeText(getApplicationContext(),  "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        redirectToHomePage();
                    }

                    @Override
                    public void onFailure(Call<AuthenticationVm> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra trong quá trình đăng nhap", Toast.LENGTH_SHORT).show();

                    }

                });
            }
        });
    }


    private void redirectToHomePage() {
        Intent activityChangeIntent = new Intent(this, MainActivity.class);
        this.startActivity(activityChangeIntent);
    }

    private void redirectToRegisterPage() {
        Intent activityChangeIntent = new Intent(this, RegisterActivity.class);
        this.startActivity(activityChangeIntent);
    }
    private void setControl() {
        ed_email_login = findViewById(R.id.ed_email_login);
        ed_password_login = findViewById(R.id.ed_password_login);
        btn_login = findViewById(R.id.btn_login);
        btn_register_home = findViewById(R.id.btn_register_home);


    }
}