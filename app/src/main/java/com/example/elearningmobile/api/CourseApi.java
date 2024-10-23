package com.example.elearningmobile.api;

import com.example.elearningmobile.model.AuthenticationPostVm;
import com.example.elearningmobile.model.AuthenticationResponse;
import com.example.elearningmobile.model.course.CourseLearningVm;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.ultity.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CourseApi {
    Gson gson = new GsonBuilder().create();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.MINUTES.SECONDS)
            .readTimeout(30, TimeUnit.MINUTES.SECONDS)
            .writeTimeout(30, TimeUnit.MINUTES.SECONDS)
            .build();
    CourseApi courseApi = new Retrofit.Builder()
            .baseUrl(Constants.COURSE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().
            create(CourseApi.class);

    // get course by category
    @GET("category/{categoryId}")
    Call<List<CourseListGetVM>> getCourseByCategory(@Path("categoryId") Integer categoryId);


    @GET("{courseId}")
    Call<CourseVM> getCourseById(@Path("courseId") Long courseId);


    @GET("{slug}/learn")
    Call<CourseLearningVm> getCoursesBySlug(@Query("slug")String slug);

    @GET("search")
    Call<List<CourseListGetVM>> getCoursesByMultiQuery();


    @GET("search")
    Call<List<CourseListGetVM>> getCoursesByMultiQuery(@Query("keyword")String keyword);

    @GET("search")
    Call<List<CourseListGetVM>> getCoursesByMultiQuery(@Query("ratingStar")Float ratingStar);

    @GET("search")
    Call<List<CourseListGetVM>> getCoursesByMultiQuery(@Query("level")String[] level);

    @GET("search")
    Call<List<CourseListGetVM>> getCoursesByMultiQuery(@Query("free")Boolean[] free);

    @GET("search")
    Call<List<CourseListGetVM>> getCoursesByMultiQueryCategory(@Query("categoryName")String categoryName);
}
