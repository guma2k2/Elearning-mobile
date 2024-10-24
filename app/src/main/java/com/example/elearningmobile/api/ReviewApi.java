package com.example.elearningmobile.api;

import com.example.elearningmobile.model.PageableData;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.review.ReviewVM;
import com.example.elearningmobile.ultity.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import kotlin.ParameterName;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReviewApi {
    Gson gson = new GsonBuilder().create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES.SECONDS)
            .readTimeout(30, TimeUnit.MINUTES.SECONDS)
            .writeTimeout(30, TimeUnit.MINUTES.SECONDS)
            .build();
    ReviewApi reviewApi = new Retrofit.Builder()
            .baseUrl(Constants.REVIEW_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().
            create(ReviewApi.class);

    // get course by category


    @GET("/search/{courseId}")
    Call<PageableData<ReviewVM>> getCourseById(
            @Path("courseId") Long courseId,
            @Query("pageNum") int pageNum,
            @Query("pageSize") int pageSize,
            @Query("ratingStar") Integer ratingStar,
            @Query("sortDir") String sortDir
    );

    @GET("/api/v1/reviews/search/{courseId}")
    Call<PageableData<ReviewVM>> getCourseById(
            @Path("courseId") Long courseId
    );



}
