package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.model.order.OrderVM;

public class CourseDetailActivity extends AppCompatActivity {

    TextView iv_courseTitle_courseDetail, iv_courseDesc_courseDetail, tv_courseRating_courseDetail
            ,tv_coursePrice_courseDetail;
    RatingBar rb_courseRating_courseDetail;
    RecyclerView rc_objectives_courseDetail, rc_curriculum_courseDetail;

    ImageView iv_courseImage_courseDetail;

    private Long courseId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setControl();
        setEvent();
    }

    private void setEvent() {


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setControl() {
        iv_courseTitle_courseDetail = findViewById(R.id.iv_courseTitle_courseDetail);
        iv_courseDesc_courseDetail = findViewById(R.id.iv_courseDesc_courseDetail);
        tv_courseRating_courseDetail = findViewById(R.id.tv_courseRating_courseDetail);
        tv_coursePrice_courseDetail = findViewById(R.id.tv_coursePrice_courseDetail);
        rb_courseRating_courseDetail = findViewById(R.id.rb_courseRating_courseDetail);
        rc_objectives_courseDetail = findViewById(R.id.rc_objectives_courseDetail);
        rc_curriculum_courseDetail = findViewById(R.id.rc_curriculum_courseDetail);
        iv_courseImage_courseDetail = findViewById(R.id.iv_courseImage_courseDetail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            courseId = extras.getLong("courseId");
        } else {
            courseId = null;
        }
    }
}