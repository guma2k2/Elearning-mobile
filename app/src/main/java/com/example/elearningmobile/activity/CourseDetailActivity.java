package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CartRecycleAdapter;
import com.example.elearningmobile.adapter.CurriculumRecycleAdapter;
import com.example.elearningmobile.adapter.ReviewRecycleAdapter;
import com.example.elearningmobile.adapter.StringRecycleAdapter;
import com.example.elearningmobile.api.CourseApi;
import com.example.elearningmobile.api.ReviewApi;
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.Curriculum;
import com.example.elearningmobile.model.PageableData;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.model.review.ReviewVM;
import com.example.elearningmobile.model.section.SectionVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tv_courseTitle_courseDetail, tv_courseDesc_courseDetail, tv_courseRating_courseDetail
            ,tv_coursePrice_courseDetail, tv_ratingCount_courseDetail, tv_studentCount_courseDetail
            ,tv_instructorName_courseDetail, tv_ratingInstructor_courseDetail, tv_reviewCountInstructor_courseDetail
            ,tv_studentCountInstructor_courseDetail;
    RatingBar rb_courseRating_courseDetail;
    RecyclerView rc_objectives_courseDetail, rc_curriculum_courseDetail, rc_reviews_courseDetail,
            rc_requirements_courseDetail;

    ImageView iv_courseImage_courseDetail, iv_instructorPhoto_courseDetail;

    private Long courseId;

    private CourseVM course = new CourseVM();

    private StringRecycleAdapter objectiveRecycleAdapter, requirementRecycleAdapter;

    private CurriculumRecycleAdapter curriculumRecycleAdapter;
    private ReviewRecycleAdapter reviewRecycleAdapter;

    private List<ReviewVM> reviewVMList = new ArrayList<>();

    private Button btn_backCourseDetail;


    private List<SectionVM> sectionVMS = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setControl();
        setEvent();
    }

    public void setCourse(CourseVM course) {
        this.course = course;
        if (course.getSections().size() > 0) {

        }
    }

    private void setEvent() {
        reviewRecycleAdapter = new ReviewRecycleAdapter(reviewVMList);
        rc_reviews_courseDetail.setAdapter(reviewRecycleAdapter);
        rc_reviews_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        objectiveRecycleAdapter = new StringRecycleAdapter(course.getObjectives(), CourseDetailType.objective);
        rc_objectives_courseDetail.setAdapter(objectiveRecycleAdapter);
        rc_objectives_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        requirementRecycleAdapter = new StringRecycleAdapter(course.getRequirements(), CourseDetailType.requirement);
        rc_requirements_courseDetail.setAdapter(requirementRecycleAdapter);
        rc_requirements_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        curriculumRecycleAdapter = new CurriculumRecycleAdapter(sectionVMS, this);
        rc_curriculum_courseDetail.setAdapter(curriculumRecycleAdapter);
        rc_curriculum_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));




        btn_backCourseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        setCourse();
        setReviews();
    }

    private void setCourse () {
        if (courseId != null) {
            CourseApi.courseApi.getCourseById(courseId).enqueue(new Callback<CourseVM>() {
                @Override
                public void onResponse(Call<CourseVM> call, Response<CourseVM> response) {
                    if (response.body() != null) {
                        CourseVM body = response.body();
                        course = body;
                        if (course.getId() != null) {
                            tv_courseTitle_courseDetail.setText(course.getTitle());
                            tv_courseDesc_courseDetail.setText(Html.fromHtml(course.getDescription()));
                            tv_courseRating_courseDetail.setText(course.getAverageRating()+"");
                            rb_courseRating_courseDetail.setRating((float) course.getAverageRating());
                            tv_ratingCount_courseDetail.setText(course.getRatingCount() + "");
                            tv_coursePrice_courseDetail.setText(PriceFormatter.formatPriceInVND(course.getPrice()));
                            Picasso.get().load(course.getUser().getPhoto()).into(iv_instructorPhoto_courseDetail);
                            tv_instructorName_courseDetail.setText(course.getUser().getFullName());
                            tv_ratingInstructor_courseDetail.setText(course.getUser().getAverageRating() + "");
                            tv_reviewCountInstructor_courseDetail.setText(course.getUser().getNumberOfReview() + "");
                            tv_studentCountInstructor_courseDetail.setText(course.getUser().getNumberOfStudent() + "");

                        }
                        List<SectionVM> sectionVMList = body.getSections();
                        sectionVMS.addAll(sectionVMList);
                        curriculumRecycleAdapter.notifyDataSetChanged();
                        objectiveRecycleAdapter.notifyDataSetChanged();
                        requirementRecycleAdapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<CourseVM> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra trong quá trình đăng nhap", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setReviews() {
        if (courseId != null) {
            ReviewApi.reviewApi.getCourseById(courseId).enqueue(new Callback<PageableData<ReviewVM>>() {
                @Override
                public void onResponse(Call<PageableData<ReviewVM>> call, Response<PageableData<ReviewVM>> response) {
                    if (response.body() != null) {
                        List<ReviewVM> reviewVMS = response.body().getContent();
                        if (reviewVMS.size() > 0 ) {
                            reviewVMList.addAll(reviewVMS);
                            reviewRecycleAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<PageableData<ReviewVM>> call, Throwable t) {
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
        tv_instructorName_courseDetail = findViewById(R.id.tv_instructorName_courseDetail);
        tv_ratingInstructor_courseDetail = findViewById(R.id.tv_ratingInstructor_courseDetail);
        tv_reviewCountInstructor_courseDetail = findViewById(R.id.tv_reviewCountInstructor_courseDetail);
        tv_studentCountInstructor_courseDetail = findViewById(R.id.tv_studentCountInstructor_courseDetail);
        rc_requirements_courseDetail = findViewById(R.id.rc_requirements_courseDetail);
        iv_instructorPhoto_courseDetail = findViewById(R.id.iv_instructorPhoto_courseDetail);
        rc_reviews_courseDetail = findViewById(R.id.rc_reviews_courseDetail);
        btn_backCourseDetail = findViewById(R.id.btn_backCourseDetail);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            courseId = extras.getLong("courseId");
        } else {
            courseId = null;
        }
    }
}