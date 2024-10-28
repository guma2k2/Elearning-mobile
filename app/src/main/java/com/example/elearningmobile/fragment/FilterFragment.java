package com.example.elearningmobile.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.adapter.CourseHomeRecycleAdapter;
import com.example.elearningmobile.adapter.CourseRecycleAdapter;
import com.example.elearningmobile.adapter.FilterRecycleAdapter;
import com.example.elearningmobile.api.CategoryApi;
import com.example.elearningmobile.api.CourseApi;
import com.example.elearningmobile.model.category.CategoryListGetVM;
import com.example.elearningmobile.model.course.CourseListGetVM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilterFragment extends Fragment {


    private Context context;

    private RecyclerView rc_filter, rc_courses_filter;

    private String keyword ;
    private SearchView sv_courseFilter;

    private Button btn_filter;
    private String categoryName;
    private List<CourseListGetVM> courseListGetVMList = new ArrayList<>();

    private List<CategoryListGetVM> categories = new ArrayList<>();

    private FilterRecycleAdapter filterRecycleAdapter;

    private CourseRecycleAdapter courseRecycleAdapter;

    private Boolean[] free;
    private String[] level;
    private Float[] rating;

    public FilterFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        setControl(view);
        setEvent();
        return view;
    }

    public void setFree(Boolean[] free) {
        this.free = free;
        setCourses();

    }

    public void setLevel(String[] level) {
        this.level = level;
        setCourses();
    }

    public void setRating(Float[] rating) {
        this.rating = rating;
        setCourses();

    }

    private void setEvent() {
        filterRecycleAdapter = new FilterRecycleAdapter(this, categories);
        rc_filter.setAdapter(filterRecycleAdapter);
        rc_filter.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));


        courseRecycleAdapter = new CourseRecycleAdapter(courseListGetVMList);
        rc_courses_filter.setAdapter(courseRecycleAdapter);
        rc_courses_filter.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));


        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilterFragment();
            }
        });

        if (keyword != "" || categoryName != null) {
            rc_filter.setVisibility(View.VISIBLE);
        } else {
            rc_filter.setVisibility(View.GONE);
        }
        sv_courseFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setKeyword(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void openFilterFragment() {
        FilterBottomFragment bottomFragment = new FilterBottomFragment(this);
        bottomFragment.show(getActivity().getSupportFragmentManager(), bottomFragment.getTag());
    }

    @Override
    public void onResume() {
        super.onResume();
        setCategories();
    }

    private void setCategories() {
        CategoryApi.categoryApi.getCategoryParent().enqueue(new Callback<List<CategoryListGetVM>>() {
            @Override
            public void onResponse(Call<List<CategoryListGetVM>> call, Response<List<CategoryListGetVM>> response) {
                List<CategoryListGetVM> categoryVMS = response.body();
                categories.addAll(categoryVMS);
                filterRecycleAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<CategoryListGetVM>> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT);
            }
        });
    }

    private void setCourses() {
        if (keyword != null || keyword != "") {
            CourseApi.courseApi.getCoursesByMultiQuery(keyword, free, rating, level, categoryName ).enqueue(new Callback<List<CourseListGetVM>>() {
                @Override
                public void onResponse(Call<List<CourseListGetVM>> call, Response<List<CourseListGetVM>> response) {
                    List<CourseListGetVM> courses = response.body();
                    courseListGetVMList.addAll(courses);
                    courseRecycleAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<List<CourseListGetVM>> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        setCourses();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        setCourses();
    }

    private void setControl(View view) {
        rc_filter = view.findViewById(R.id.rc_filter);
        sv_courseFilter = view.findViewById(R.id.sv_courseFilter);
        btn_filter = view.findViewById(R.id.btn_filter);
        rc_courses_filter = view.findViewById(R.id.rc_courses_filter);
    }
}