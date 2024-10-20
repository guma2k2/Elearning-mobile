package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CartRecycleAdapter;
import com.example.elearningmobile.adapter.CurriculumRecycleAdapter;
import com.example.elearningmobile.adapter.StringRecycleAdapter;
import com.example.elearningmobile.api.CourseApi;
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.ultity.PriceFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tv_courseTitle_courseDetail, tv_courseDesc_courseDetail, tv_courseRating_courseDetail
            ,tv_coursePrice_courseDetail, tv_ratingCount_courseDetail, tv_studentCount_courseDetail;
    RatingBar rb_courseRating_courseDetail;
    RecyclerView rc_objectives_courseDetail, rc_curriculum_courseDetail;

    ImageView iv_courseImage_courseDetail;

    private Long courseId;

    private CourseVM course = null;

    private StringRecycleAdapter objectiveRecycleAdapter;

    private CurriculumRecycleAdapter curriculumRecycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setControl();
        setEvent();
    }

    private void setEvent() {
        if (course != null) {
            tv_courseTitle_courseDetail.setText(course.getTitle());
            tv_courseDesc_courseDetail.setText(Html.fromHtml(course.getDescription()));
            tv_courseRating_courseDetail.setText(course.getAverageRating()+"");
            rb_courseRating_courseDetail.setRating((float) course.getAverageRating());
            tv_ratingCount_courseDetail.setText(course.getRatingCount());
            tv_coursePrice_courseDetail.setText(PriceFormatter.formatPriceInVND(course.getPrice()));


            objectiveRecycleAdapter = new StringRecycleAdapter(course.getObjectives(), CourseDetailType.objective);
            rc_objectives_courseDetail.setAdapter(objectiveRecycleAdapter);
            rc_objectives_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


            curriculumRecycleAdapter = new CurriculumRecycleAdapter(course, this);
            rc_curriculum_courseDetail.setAdapter(curriculumRecycleAdapter);
            rc_curriculum_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        }

    }



    @Override
    protected void onResume() {
        super.onResume();
        setCourse();
    }

    private void setCourse () {
        if (courseId != null) {
            CourseApi.courseApi.getCourseById(courseId).enqueue(new Callback<CourseVM>() {
                @Override
                public void onResponse(Call<CourseVM> call, Response<CourseVM> response) {
                    if (response.body() != null) {
                        course = response.body();
                    }
                }

                @Override
                public void onFailure(Call<CourseVM> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra trong quá trình đăng nhap", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setControl() {
        tv_courseTitle_courseDetail = findViewById(R.id.tv_courseTitle_courseDetail);
        tv_courseDesc_courseDetail = findViewById(R.id.tv_courseDesc_courseDetail);
        tv_courseRating_courseDetail = findViewById(R.id.tv_courseRating_courseDetail);
        tv_coursePrice_courseDetail = findViewById(R.id.tv_coursePrice_courseDetail);
        rb_courseRating_courseDetail = findViewById(R.id.rb_courseRating_courseDetail);
        rc_objectives_courseDetail = findViewById(R.id.rc_objectives_courseDetail);
        rc_curriculum_courseDetail = findViewById(R.id.rc_curriculum_courseDetail);
        iv_courseImage_courseDetail = findViewById(R.id.iv_courseImage_courseDetail);
        tv_ratingCount_courseDetail = findViewById(R.id.tv_ratingCount_courseDetail);
        tv_studentCount_courseDetail = findViewById(R.id.tv_studentCount_courseDetail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            courseId = extras.getLong("courseId");
        } else {
            courseId = null;
        }
    }
}