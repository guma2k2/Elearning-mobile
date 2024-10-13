package com.example.elearningmobile.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CategoryHomeRecycleAdapter;
import com.example.elearningmobile.adapter.CourseHomeRecycleAdapter;
import com.example.elearningmobile.api.CategoryApi;
import com.example.elearningmobile.api.CourseApi;
import com.example.elearningmobile.api.OrderApi;
import com.example.elearningmobile.model.category.CategoryListGetVM;
import com.example.elearningmobile.model.category.CategoryVM;
import com.example.elearningmobile.model.course.CourseListGetVM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private Context context;
    RecyclerView rc_top_courses, rc_courses_category, rc_category_home;
    TextView tv_home_category;
    List<CourseListGetVM> coursesByCategory, coursesBestSeller;

    List<CategoryListGetVM> categories;


    CourseHomeRecycleAdapter courseCategoryRecycleAdapter, courseBestSellerRecycleAdapter;

    CategoryHomeRecycleAdapter categoryHomeRecycleAdapter;

    public HomeFragment(Context context) {
        this.context = context;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        setControl(view);

        setCategories();
        setCoursesByCategory();
        setCoursesBestSeller();

        courseCategoryRecycleAdapter = new CourseHomeRecycleAdapter(coursesByCategory);
        rc_courses_category.setAdapter(courseCategoryRecycleAdapter);
        rc_courses_category.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));


        courseBestSellerRecycleAdapter = new CourseHomeRecycleAdapter(coursesBestSeller);
        rc_top_courses.setAdapter(courseBestSellerRecycleAdapter);
        rc_top_courses.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        categoryHomeRecycleAdapter = new CategoryHomeRecycleAdapter(categories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false);
        rc_category_home.setLayoutManager(gridLayoutManager);
        return view;
    }

    private void setCategories() {
        CategoryApi.categoryApi.getCategoryParent().enqueue(new Callback<List<CategoryListGetVM>>() {
            @Override
            public void onResponse(Call<List<CategoryListGetVM>> call, Response<List<CategoryListGetVM>> response) {
                List<CategoryListGetVM> categoryVMS = response.body();
                categories.addAll(categoryVMS);
            }

            @Override
            public void onFailure(Call<List<CategoryListGetVM>> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT);
            }
        });
    }

    private void setCoursesBestSeller() {
        OrderApi.orderApi.getBestSellerCourse().enqueue(new Callback<List<CourseListGetVM>>() {
            @Override
            public void onResponse(Call<List<CourseListGetVM>> call, Response<List<CourseListGetVM>> response) {
                List<CourseListGetVM> res = response.body();
                coursesBestSeller.addAll(res);
            }

            @Override
            public void onFailure(Call<List<CourseListGetVM>> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT);

            }
        });
    }

    private void setCoursesByCategory() {
        final Integer categoryId = 14;
        CourseApi.courseApi.getCourseByCategory(categoryId).enqueue(new Callback<List<CourseListGetVM>>() {
            @Override
            public void onResponse(Call<List<CourseListGetVM>> call, Response<List<CourseListGetVM>> response) {
                List<CourseListGetVM> res = response.body();
                coursesByCategory.addAll(res);
            }

            @Override
            public void onFailure(Call<List<CourseListGetVM>> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT);
            }
        });
    }

    private void setControl(View view) {
        rc_top_courses = view.findViewById(R.id.rc_top_courses);
        rc_courses_category = view.findViewById(R.id.rc_courses_category);
        tv_home_category = view.findViewById(R.id.tv_home_category);
        rc_category_home = view.findViewById(R.id.rc_category_home);
        coursesByCategory = new ArrayList<>();
        coursesBestSeller = new ArrayList<>();
        categories = new ArrayList<>();
    }
}