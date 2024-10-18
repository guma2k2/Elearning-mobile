package com.example.elearningmobile.api;

import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.ultity.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface OrderApi {

    Gson gson = new GsonBuilder().create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES.SECONDS)
            .readTimeout(30, TimeUnit.MINUTES.SECONDS)
            .writeTimeout(30, TimeUnit.MINUTES.SECONDS)
            .build();
    OrderApi orderApi = new Retrofit.Builder()
            .baseUrl(Constants.ORDER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().
            create(OrderApi.class);

    @GET("beseller-courses")
    Call<List<CourseListGetVM>> getBestSellerCourse();

    @GET("user")
    Call<List<OrderVM>> getOrdersByUser(@Header("Authorization") String token);
}
