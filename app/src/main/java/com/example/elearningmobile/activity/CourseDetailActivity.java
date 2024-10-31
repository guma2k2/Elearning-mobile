package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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
import com.example.elearningmobile.model.PageableData;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.review.ReviewVM;
import com.example.elearningmobile.model.section.SectionVM;
import com.example.elearningmobile.ultity.PriceFormatter;
import com.squareup.picasso.Picasso;
import com.vnpay.authentication.VNP_AuthenticationActivity;
import com.vnpay.authentication.VNP_SdkCompletedCallback;

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

    private Button btn_backCourseDetail, btn_buy_courseDetail;


    private List<SectionVM> sectionVMS = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        setControl();
        setEvent();
    }


    public void setSectionVMS(List<SectionVM> sectionVMList) {
        curriculumRecycleAdapter.notifyDataSetChanged();
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



        btn_buy_courseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSdk();
            }
        });


        btn_backCourseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToHomePage();
            }
        });
    }

    private void redirectToHomePage() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("fragment", R.id.nav_home);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void openSdk() {
        Intent intent = new Intent(this, VNP_AuthenticationActivity.class);
        intent.putExtra("url", "https://sandbox.vnpayment.vn/testsdk"); //bắt buộc, VNPAY cung cấp
        intent.putExtra("tmn_code", "9DL8WGFX"); //bắt buộc, VNPAY cung cấp
        intent.putExtra("scheme", "resultactivity"); //bắt buộc, scheme để mở lại app khi có kết quả thanh toán từ mobile banking
        intent.putExtra("is_sandbox", false); //bắt buộc, true <=> môi trường test, true <=> môi trường live


        VNP_AuthenticationActivity.setSdkCompletedCallback(new VNP_SdkCompletedCallback() {
            @Override
            public void sdkAction(String action) {
                Log.wtf("SplashActivity", "action: " + action);
                //action == AppBackAction
                //Người dùng nhấn back từ sdk để quay lại

                //action == CallMobileBankingApp
                //Người dùng nhấn chọn thanh toán qua app thanh toán (Mobile Banking, Ví...)
                //lúc này app tích hợp sẽ cần lưu lại cái PNR, khi nào người dùng mở lại app tích hợp thì sẽ gọi kiểm tra trạng thái thanh toán của PNR Đó xem đã thanh toán hay chưa.

                //action == WebBackAction
                //Người dùng nhấn back từ trang thanh toán thành công khi thanh toán qua thẻ khi url có chứa: cancel.sdk.merchantbackapp

                //action == FaildBackAction
                //giao dịch thanh toán bị failed

                //action == SuccessBackAction
                //thanh toán thành công trên webview
            }
        });
        startActivity(intent);
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
        btn_buy_courseDetail = findViewById(R.id.btn_buy_courseDetail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            courseId = extras.getLong("courseId");
        } else {
            courseId = null;
        }
    }
}