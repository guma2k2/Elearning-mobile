package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.AnswerRecycleAdapter;
import com.example.elearningmobile.adapter.LearningRecycleAdapter;
import com.example.elearningmobile.adapter.StringRecycleAdapter;
import com.example.elearningmobile.adapter.learning.CurriculumLearningRecycleAdapter;
import com.example.elearningmobile.api.CourseApi;
import com.example.elearningmobile.model.AnswerVM;
import com.example.elearningmobile.model.CourseDetailType;
import com.example.elearningmobile.model.Curriculum;
import com.example.elearningmobile.model.LectureVm;
import com.example.elearningmobile.model.QuestionVM;
import com.example.elearningmobile.model.QuizVM;
import com.example.elearningmobile.model.course.CourseLearningVm;
import com.example.elearningmobile.model.course.CourseListGetVM;
import com.example.elearningmobile.model.course.CourseVM;
import com.example.elearningmobile.model.order.OrderVM;
import com.example.elearningmobile.model.section.SectionVM;
import com.example.elearningmobile.variable.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningActivity extends AppCompatActivity {

    public VideoView vv_learning;
    public RecyclerView rc_lectures_learning;

    private CourseLearningVm courseLearningVm;

    public boolean showingAnswer = false;

    private List<SectionVM> sectionVMList = new ArrayList<>();

    public Integer indexQuestion = 0;

    public Integer selectedAnswerIndex = 0;

    public int selectedPosition = -1;

    private Button btn_back_to_learning;

    public Long curriculumId ;
    public String type;

    private LinearLayout ll_quiz;
    private TextView tv_questionText ,tv_correctAnswer;
    private RadioGroup rg_answers;

    private RecyclerView rc_answers;

    public Button btn_answer, btn_nextQuestion;

    private List<AnswerVM> answers = new ArrayList<>();

    public AnswerRecycleAdapter answerRecycleAdapter;

    public CurriculumLearningRecycleAdapter curriculumLearningRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        setControl();
        setEvent();
    }

    private void setEvent() {

        Curriculum curriculum = getCurrentCurriculum(curriculumId, type);
        if (curriculum instanceof QuizVM) {
            int maxQuestion = ((QuizVM) curriculum).getQuestions().size();
            if (indexQuestion + 2 == maxQuestion) {
                btn_nextQuestion.setText("Làm lại");
            }
        }

        answerRecycleAdapter = new AnswerRecycleAdapter(answers, this);
        rc_answers.setAdapter(answerRecycleAdapter);
        rc_answers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        curriculumLearningRecycleAdapter = new CurriculumLearningRecycleAdapter(sectionVMList, this);
        rc_lectures_learning.setAdapter(curriculumLearningRecycleAdapter);
        rc_lectures_learning.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        btn_back_to_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToLearning();
            }
        });

        btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition == -1) {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn câu trả lời", Toast.LENGTH_SHORT).show();
                } else {
                    showingAnswer = true;
                    Curriculum curriculum = getCurrentCurriculum(curriculumId, type);
                    if (curriculum instanceof QuizVM) {
                        QuestionVM questionVM = ((QuizVM) curriculum).getQuestions().get(indexQuestion);
                        for (AnswerVM answerVM : questionVM.getAnswers()) {
                            if (answerVM.isCorrect() && answerVM.getReason() != null){
                                tv_correctAnswer.setVisibility(View.VISIBLE);
                                tv_correctAnswer.setText(Html.fromHtml(answerVM.getReason()));

                            }
                        }
                    }
                    answerRecycleAdapter.notifyDataSetChanged();
                }
            }
        });


        btn_nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_correctAnswer.setVisibility(View.GONE);
                Curriculum curriculum = getCurrentCurriculum(curriculumId, type);
                if (curriculum instanceof QuizVM) {
                    int maxQuestion = ((QuizVM) curriculum).getQuestions().size();
                    if (indexQuestion + 2 > maxQuestion) {
                        btn_nextQuestion.setText("Câu tiếp theo");
                        showingAnswer = false;
                        selectedPosition = -1;
                        indexQuestion = 0;
                        QuestionVM questionVM = ((QuizVM) curriculum).getQuestions().get(indexQuestion);
                        tv_questionText.setText(String.format("Câu %d:", indexQuestion + 1) + Html.fromHtml(questionVM.getTitle()));
                        List<AnswerVM> answerVMS = questionVM.getAnswers();
                        answers.clear();
                        answers.addAll(answerVMS);
                        answerRecycleAdapter.notifyDataSetChanged();
                    }else {
                        if (!showingAnswer) {
                            Toast.makeText(getApplicationContext(), "Vui lòng trả lời câu hỏi trước", Toast.LENGTH_SHORT).show();
                        } else {
                            if (indexQuestion + 2 == maxQuestion) {
                                btn_nextQuestion.setText("Làm lại");
                            }
                            showingAnswer = false;
                            selectedPosition = -1;
                            indexQuestion = indexQuestion + 1;
                            QuestionVM questionVM = ((QuizVM) curriculum).getQuestions().get(indexQuestion);
                            tv_questionText.setText(String.format("Câu %d:", indexQuestion + 1) + Html.fromHtml(questionVM.getTitle()));
                            List<AnswerVM> answerVMS = questionVM.getAnswers();
                            answers.clear();
                            answers.addAll(answerVMS);
                            answerRecycleAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    private void redirectToLearning() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("fragment", R.id.nav_learning);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    void getCourseLearning () {
        Bundle extras = getIntent().getExtras();

        GlobalVariable globalVariable = (GlobalVariable) getApplication();
//        if (extras != null) {
//            String slug = extras.getString("slug");
            String slug = "my-course-is-number-one";
//            String token = globalVariable.getAccess_token();
            String token = "eyJhbGciOiJIUzI1NiJ9.eyJzY29wZXMiOlsiUk9MRV9TVFVERU5UIl0sInN1YiI6Im4yMGRjY24xNTNAc3R1ZGVudC5wdGl0aGNtLmVkdS52biIsImlhdCI6MTczMzE0OTkwOCwiZXhwIjoxNzM0NDQ1OTA4fQ.tEFUtLRsHAVhl_MLLjwyON3Fz3kXuphYiTEip1ilkz0";

            String bearerToken = "Bearer " + token;
            if (slug != null && slug != "") {
                CourseApi.courseApi.getCoursesBySlug(slug, bearerToken).enqueue(new Callback<CourseLearningVm>() {
                    @Override
                    public void onResponse(Call<CourseLearningVm> call, Response<CourseLearningVm> response) {
                        if (response != null) {
                            CourseLearningVm body = response.body();
                            courseLearningVm = body;
                            sectionVMList.addAll(body.getCourse().getSections());
                            type = body.getType();
                            curriculumId = body.getCurriculumId();
                            Curriculum curriculum = getCurrentCurriculum(courseLearningVm.getSectionId(), courseLearningVm.getCurriculumId(), body.getType())  ;
                            if (curriculum instanceof LectureVm) {
                                ll_quiz.setVisibility(View.GONE);
                                vv_learning.setVisibility(View.VISIBLE);
                                String videoUrl = ((LectureVm) curriculum).getVideoId();
                                runVideo(videoUrl);
                            } else if (curriculum instanceof QuizVM) {
                                QuizVM quizVM = (QuizVM) curriculum;
                                showQuestion(quizVM);
                            }

                            curriculumLearningRecycleAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<CourseLearningVm> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT);
                    }
                });
            }
//        }
    }

    public void showQuestion(QuizVM quizVM) {
        showingAnswer = false;
        tv_correctAnswer.setVisibility(View.GONE);
        ll_quiz.setVisibility(View.VISIBLE);
        vv_learning.setVisibility(View.GONE);
        QuestionVM questionVM = quizVM.getQuestions().get(indexQuestion);
        tv_questionText.setText(String.format("Câu %d:", indexQuestion + 1) + Html.fromHtml(questionVM.getTitle()));
        List<AnswerVM> answerVMS = questionVM.getAnswers();
        answers.clear();
        answers.addAll(answerVMS);
        answerRecycleAdapter.notifyDataSetChanged();
    }
    public void runVideo(String url) {
        tv_correctAnswer.setVisibility(View.GONE);
        ll_quiz.setVisibility(View.GONE);
        vv_learning.setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(url);
        vv_learning.setVideoURI(uri);
        vv_learning.requestFocus();
        vv_learning.start();
    }

    private Curriculum getCurrentCurriculum(Long sectionId, Long curriculumId, String type) {
        for (SectionVM section : sectionVMList) {
            if (section.getId().equals(sectionId)) {
                // Iterate over the curriculum list in the found section
                for (Curriculum curriculum : section.getCurriculums()) {
                    if (curriculum.getId().equals(curriculumId) && curriculum.getType().name().equals(type)) {
                        return curriculum;
                    }
                }
            }
        }
        // Return null if no matching curriculum is found
        return null;
    }


    private Curriculum getCurrentCurriculum(Long curriculumId, String type) {
        for (SectionVM section : sectionVMList) {
                // Iterate over the curriculum list in the found section
            for (Curriculum curriculum : section.getCurriculums()) {
                if (curriculum.getId().equals(curriculumId) && curriculum.getType().name().equals(type)) {
                    return curriculum;
                }
            }
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCourseLearning();
    }

    private void setControl() {
        rc_lectures_learning= findViewById(R.id.rc_lectures_learning);
        vv_learning= findViewById(R.id.vv_learning);
        btn_back_to_learning = findViewById(R.id.btn_back_to_learning);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv_learning);
        vv_learning.setMediaController(mediaController);


        ll_quiz = findViewById(R.id.ll_quiz);
        tv_questionText = findViewById(R.id.tv_questionText);
        rg_answers = findViewById(R.id.rg_answers);
        rc_answers = findViewById(R.id.rc_answers);
        btn_answer = findViewById(R.id.btn_answer);
        btn_nextQuestion = findViewById(R.id.btn_nextQuestion);
        tv_correctAnswer = findViewById(R.id.tv_correctAnswer);
    }
}