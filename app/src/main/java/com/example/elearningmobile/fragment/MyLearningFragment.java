package com.example.elearningmobile.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CartRecycleAdapter;
import com.example.elearningmobile.adapter.LearningRecycleAdapter;
import com.example.elearningmobile.api.learning.MyLearningCourseApi;
import com.example.elearningmobile.model.cart.CartListGetVM;
import com.example.elearningmobile.model.learning.LearningCourseVM;
import com.example.elearningmobile.variable.GlobalVariable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MyLearningFragment extends Fragment {
    private Context context;
    private GlobalVariable globalVariable ;

    private LearningRecycleAdapter learningRecycleAdapter;
    private RecyclerView rc_learning;

    private List<LearningCourseVM> learningCourseVMS;

    public MyLearningFragment(Context context, GlobalVariable globalVariable) {
        this.context = context;
        this.globalVariable = globalVariable;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_learning, container, false);
        setLearningCourses();
        setControl(view);
        setEvent();
        return view;
    }

    private void setEvent() {
        learningRecycleAdapter = new LearningRecycleAdapter(learningCourseVMS);
        rc_learning.setAdapter(learningRecycleAdapter);
        rc_learning.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    private void setLearningCourses() {
        if (globalVariable.isLoggedIn()) {
            String token = globalVariable.getAccess_token();
            MyLearningCourseApi.myLearningCourseApi.getByStudent(token).enqueue(new Callback<List<LearningCourseVM>>() {
                @Override
                public void onResponse(Call<List<LearningCourseVM>> call, Response<List<LearningCourseVM>> response) {
                    List<LearningCourseVM> res = response.body();
                    learningCourseVMS.addAll(res);
                }

                @Override
                public void onFailure(Call<List<LearningCourseVM>> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setLearningCourses();
    }
    private void setControl(View view) {
        learningCourseVMS = new ArrayList<>();
        rc_learning = view.findViewById(R.id.rc_learning);
    }
}