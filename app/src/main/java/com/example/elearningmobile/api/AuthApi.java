package com.example.elearningmobile.api;

import com.example.elearningmobile.model.AuthenticationPostVm;
import com.example.elearningmobile.model.AuthenticationResponse;
import com.example.elearningmobile.model.AuthenticationVm;
import com.example.elearningmobile.model.RegistrationPostVm;
import com.example.elearningmobile.ultity.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.Call;
import retrofit2.http.POST;

public interface AuthApi {
    Gson gson = new GsonBuilder().create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES.SECONDS)
            .readTimeout(30, TimeUnit.MINUTES.SECONDS)
            .writeTimeout(30, TimeUnit.MINUTES.SECONDS)
            .build();
    AuthApi authApi = new Retrofit.Builder()
            .baseUrl(Constants.AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().
            create(AuthApi.class);

    @POST("login")
    Call<AuthenticationVm> login(@Body AuthenticationPostVm authenticationPostVm);

    @POST("register")
    Call<AuthenticationVm> register(@Body RegistrationPostVm registrationPostVm);
}
