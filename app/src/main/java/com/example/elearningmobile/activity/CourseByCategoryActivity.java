package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CourseRecycleAdapter;
import com.example.elearningmobile.api.CourseApi;
import com.example.elearningmobile.model.course.CourseListGetVM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseByCategoryActivity extends AppCompatActivity {


    Button btn_back_to_home;

    RecyclerView rc_courseByCategory;

    TextView tv_categoryName_home;

    CourseRecycleAdapter courseRecycleAdapter;

    List<CourseListGetVM> courseListGetVMList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_by_category);
        setControl();
        setEvent();
    }

    private void setEvent() {

        courseRecycleAdapter = new CourseRecycleAdapter(courseListGetVMList);
        rc_courseByCategory.setAdapter(courseRecycleAdapter);
        rc_courseByCategory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        btn_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToHomePage();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCourses();
    }

    private void redirectToHomePage() {
        Intent activityChangeIntent = new Intent(this, MainActivity.class);
        this.startActivity(activityChangeIntent);
    }

    private void setCourses() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Integer categoryId = extras.getInt("categoryId");
            String categoryName = extras.getString("categoryName");
            tv_categoryName_home.setText(categoryName);

            CourseApi.courseApi.getCourseByCategory(categoryId).enqueue(new Callback<List<CourseListGetVM>>() {
                @Override
                public void onResponse(Call<List<CourseListGetVM>> call, Response<List<CourseListGetVM>> response) {
                    List<CourseListGetVM> courses = response.body();
                    courseListGetVMList.clear();
                    courseListGetVMList.addAll(courses);
                    courseRecycleAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<CourseListGetVM>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

    private void setControl() {
        btn_back_to_home = findViewById(R.id.btn_back_to_home);
        rc_courseByCategory = findViewById(R.id.rc_courseByCategory);
        tv_categoryName_home = findViewById(R.id.tv_categoryName_home);
    }
}