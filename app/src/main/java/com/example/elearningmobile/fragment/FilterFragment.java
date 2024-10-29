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

    public List<Boolean> frees = new ArrayList<>();
    public List<String> levels = new ArrayList<>();
    public Float rating;

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



    public void setRating(Float rating) {
        this.rating = rating;
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
        if (courseListGetVMList.size() > 0 ) {
            rc_filter.setVisibility(View.GONE);
        }else {
            rc_filter.setVisibility(View.VISIBLE);
        }
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

    public void setCourses() {

        Boolean[] free = getFreeArray();
        String[] level = getLevelArray();
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

    private String[] getLevelArray() {
        int size = levels.size() ;
        if (size > 0) {
            String[] levelArray = new String[size];
            int start = 0;
            for(String b : levels) {
                levelArray[start++] = b;
            }
        }
        return new String[]{};
    }

    private Boolean[] getFreeArray() {
        int size = frees.size() ;
        if (size > 0) {
            Boolean[] free = new Boolean[size];
            int start = 0;
            for(Boolean b : frees) {
                free[start++] = b;
            }
        }
        return new Boolean[]{};
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

    public void addLevel(String level) {
        levels.add(level);
    }

    public void removeLevel(String level) {
        for (String l : levels) {
            if (l.equals(level)) {
                levels.remove(l);
            }
        }
    }

    public void addFree(boolean f) {
        frees.add(f);
    }

    public void removeFree(boolean f) {
        for (Boolean item : frees) {
            if (f == item) {
                frees.remove(f);
            }
        }
    }
}