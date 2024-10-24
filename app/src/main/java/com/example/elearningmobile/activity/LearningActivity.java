package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.LearningRecycleAdapter;
import com.example.elearningmobile.adapter.learning.CurriculumLearningRecycleAdapter;
import com.example.elearningmobile.api.CourseApi;
import com.example.elearningmobile.model.course.CourseLearningVm;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.order.OrderVM;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningActivity extends AppCompatActivity {

    public VideoView vv_learning;
    public RecyclerView rc_lectures_learning;

    private CourseLearningVm courseLearningVm;

    private CurriculumLearningRecycleAdapter curriculumLearningRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        setControl();
        setEvent();
    }

    private void setEvent() {
        if (courseLearningVm != null) {

            Uri videoUri = Uri.parse("");

            // Set the video URI
            vv_learning.setVideoURI(videoUri);

            // Create and set MediaController to allow play/pause controls
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(vv_learning);
            vv_learning.setMediaController(mediaController);

            // Start the video
            vv_learning.start();
            curriculumLearningRecycleAdapter = new CurriculumLearningRecycleAdapter(courseLearningVm, this);
            rc_lectures_learning.setAdapter(curriculumLearningRecycleAdapter);
            rc_lectures_learning.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }

    }


    void getCourseLearning () {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String slug = extras.getString("slug");
            if (slug != null && slug != "") {
                CourseApi.courseApi.getCoursesBySlug(slug).enqueue(new Callback<CourseLearningVm>() {
                    @Override
                    public void onResponse(Call<CourseLearningVm> call, Response<CourseLearningVm> response) {
                        if (response != null) {
                            CourseLearningVm body = response.body();
                            courseLearningVm = body;
                            curriculumLearningRecycleAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<CourseLearningVm> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT);
                    }
                });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCourseLearning();
    }

    private void setControl() {
        rc_lectures_learning= findViewById(R.id.rc_lectures_learning);
        vv_learning= findViewById(R.id.vv_learning);
    }
}