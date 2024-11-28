package com.example.elearningmobile.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import com.example.elearningmobile.api.CreateOrder;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class CourseDetailActivity extends AppCompatActivity {

    TextView tv_courseTitle_courseDetail, tv_courseDesc_courseDetail, tv_courseRating_courseDetail
            ,tv_coursePrice_courseDetail, tv_ratingCount_courseDetail, tv_courseCountInstructor_courseDetail
            ,tv_instructorName_courseDetail, tv_ratingInstructor_courseDetail, tv_reviewCountInstructor_courseDetail
            ,tv_studentCountInstructor_courseDetail, tv_courseHeadline_courseDetail, tv_courseAvgRating,
            tv_requirementTitle, tv_courseObjectiveTitle;
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


    private List<String> requirements = new ArrayList<>();

    private List<String> objectives = new ArrayList<>();

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


        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);
        // bind components with ids
        // handle CreateOrder
        btn_buy_courseDetail.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                CreateOrder orderApi = new CreateOrder();

                try {
                    JSONObject data = orderApi.createOrder(course.getPrice().toString());
                    String code = data.getString("return_code");

                    if (code.equals("1")) {
                        String token = data.getString("zp_trans_token");
                        ZaloPaySDK.getInstance().payOrder(CourseDetailActivity.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        new AlertDialog.Builder(CourseDetailActivity.this)
                                                .setTitle("Payment Success")
                                                .setMessage(String.format("TransactionId: %s - TransToken: %s", transactionId, transToken))
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                })
                                                .setNegativeButton("Cancel", null).show();
                                    }

                                });

                                IsLoading();
                            }

                            @Override
                            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                new AlertDialog.Builder(CourseDetailActivity.this)
                                        .setTitle("User Cancel Payment")
                                        .setMessage(String.format("zpTransToken: %s \n", zpTransToken))
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .setNegativeButton("Cancel", null).show();
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                new AlertDialog.Builder(CourseDetailActivity.this)
                                        .setTitle("Payment Fail")
                                        .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayError.toString(), zpTransToken))
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .setNegativeButton("Cancel", null).show();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        reviewRecycleAdapter = new ReviewRecycleAdapter(reviewVMList);
        rc_reviews_courseDetail.setAdapter(reviewRecycleAdapter);
        rc_reviews_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        objectiveRecycleAdapter = new StringRecycleAdapter(objectives, CourseDetailType.objective);
        rc_objectives_courseDetail.setAdapter(objectiveRecycleAdapter);
        rc_objectives_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        requirementRecycleAdapter = new StringRecycleAdapter(requirements, CourseDetailType.requirement);
        rc_requirements_courseDetail.setAdapter(requirementRecycleAdapter);
        rc_requirements_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        curriculumRecycleAdapter = new CurriculumRecycleAdapter(sectionVMS, this);
        rc_curriculum_courseDetail.setAdapter(curriculumRecycleAdapter);
        rc_curriculum_courseDetail.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));





        btn_backCourseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToHomePage();
            }
        });
    }

    private void IsLoading() {
        // Todo: hide btn buy
    }

    private void redirectToHomePage() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("fragment", R.id.nav_home);
        intent.putExtras(bundle);
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
                            tv_ratingCount_courseDetail.setText(String.format("%d đánh giá", course.getRatingCount()));
                            tv_coursePrice_courseDetail.setText(PriceFormatter.formatPriceInVND(course.getPrice()));
                            Picasso.get().load(course.getUser().getPhoto()).into(iv_instructorPhoto_courseDetail);
                            tv_instructorName_courseDetail.setText(course.getUser().getFullName());
                            tv_ratingInstructor_courseDetail.setText(String.format("%f xếp hạng giảng viên", course.getUser().getAverageRating()));
                            tv_reviewCountInstructor_courseDetail.setText(String.format("%d đánh giá", course.getUser().getNumberOfReview()));
                            tv_studentCountInstructor_courseDetail.setText(String.format("%d học viên", course.getUser().getNumberOfStudent()));
                            tv_courseCountInstructor_courseDetail.setText(String.format("%d khóa học", course.getUser().getNumberOfCourse()));
                            tv_courseHeadline_courseDetail.setText(course.getHeadline());
                            tv_courseAvgRating.setText(String.format("%f xếp hạng khóa học", course.getAverageRating()));

                            if (course.getRequirements().length == 0 ) {
                                tv_requirementTitle.setVisibility(View.GONE);
                            }else {
                                tv_requirementTitle.setVisibility(View.VISIBLE);

                                List<String> requirementList = new ArrayList<>();
                                for(String value : course.getRequirements()) {
                                    requirementList.add(value);
                                }
                                requirements.addAll(requirementList);
                            }

                            if (course.getObjectives().length == 0 ) {
                                tv_courseObjectiveTitle.setVisibility(View.GONE);
                            }else {
                                tv_courseObjectiveTitle.setVisibility(View.VISIBLE);

                                List<String> values = new ArrayList<>();
                                for(String value : course.getObjectives()) {
                                    values.add(value);
                                }
                                objectives.addAll(values);
                            }

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
        tv_instructorName_courseDetail = findViewById(R.id.tv_instructorName_courseDetail);
        tv_ratingInstructor_courseDetail = findViewById(R.id.tv_ratingInstructor_courseDetail);
        tv_reviewCountInstructor_courseDetail = findViewById(R.id.tv_reviewCountInstructor_courseDetail);
        tv_studentCountInstructor_courseDetail = findViewById(R.id.tv_studentCountInstructor_courseDetail);
        rc_requirements_courseDetail = findViewById(R.id.rc_requirements_courseDetail);
        iv_instructorPhoto_courseDetail = findViewById(R.id.iv_instructorPhoto_courseDetail);
        rc_reviews_courseDetail = findViewById(R.id.rc_reviews_courseDetail);
        btn_backCourseDetail = findViewById(R.id.btn_backCourseDetail);
        btn_buy_courseDetail = findViewById(R.id.btn_buy_courseDetail);
        tv_courseHeadline_courseDetail = findViewById(R.id.tv_courseHeadline_courseDetail);
        tv_courseCountInstructor_courseDetail = findViewById(R.id.tv_courseCountInstructor_courseDetail);
        tv_courseAvgRating = findViewById(R.id.tv_courseAvgRating);
        tv_requirementTitle = findViewById(R.id.tv_requirementTitle);
        tv_courseObjectiveTitle = findViewById(R.id.tv_courseObjectiveTitle);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            courseId = extras.getLong("courseId");
        } else {
            courseId = null;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}