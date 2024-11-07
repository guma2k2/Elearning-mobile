package com.example.elearningmobile.api;

import com.example.elearningmobile.model.AuthenticationPostVm;
import com.example.elearningmobile.model.AuthenticationVm;
import com.example.elearningmobile.model.OutboundUserRequest;
import com.example.elearningmobile.model.RegistrationPostVm;
import com.example.elearningmobile.model.StudentPutVM;
import com.example.elearningmobile.model.UserVm;
import com.example.elearningmobile.ultity.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface StudentApi {
    Gson gson = new GsonBuilder().create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES.SECONDS)
            .readTimeout(30, TimeUnit.MINUTES.SECONDS)
            .writeTimeout(30, TimeUnit.MINUTES.SECONDS)
            .build();
    StudentApi studentApi = new Retrofit.Builder()
            .baseUrl(Constants.AUTH_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().
            create(StudentApi.class);

    @PUT("/students/profile")
    Call<UserVm> updateProfile(@Body StudentPutVM studentPutVM);


}
