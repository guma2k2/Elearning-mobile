package com.example.elearningmobile.fragment;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elearningmobile.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class FilterBottomFragment extends BottomSheetDialogFragment {
    BottomSheetDialog dialog;


    private FilterFragment filterFragment;
    BottomSheetBehavior<View> bottomSheetBehavior ;

    private final static String Intermediate = "Intermediate";
    private final static String Beginner = "Beginner";

    private final static String Expert = "Expert";

    private final static String AllLevel = "AllLevel";



    View rootView ;
    RadioGroup rg_ratings ;

    CheckBox cb_free_filter, cb_paid_filter, cb_beginner_filter, cb_all_levels_filter, cb_intermediate_filter, cb_expert_filter;
    TextView btn_reset_filter;

    RadioButton rb_45_filter, rb_40_filter, rb_35_filter, rb_30_filter;
    ImageView iv_dismiss_filter;
    Button btn_apply_filter;




    List<Float> rating = new ArrayList<>();
    public FilterBottomFragment(FilterFragment filterFragment) {
        this.filterFragment = filterFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);;
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.bottom_filter_sheet, container, false);
        setControl(rootView);
        setEvent();
        return rootView;
    }

    private void setEvent() {

        rg_ratings.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rb_45_filter.isChecked()) {
                    filterFragment.setRating(3.0f);
                } else if (rb_40_filter.isChecked()) {
                    filterFragment.setRating(3.5f);
                } else if (rb_35_filter.isChecked()) {
                    filterFragment.setRating(4.0f);
                } else if (rb_30_filter.isChecked()) {
                    filterFragment.setRating(4.5f);
                }
            }
        });

        btn_reset_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSearch();
            }
        });
        iv_dismiss_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_apply_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterFragment.setCourses();
                dismiss();
            }
        });


        cb_free_filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   filterFragment.addFree(true);
                } else {
                    filterFragment.removeFree(true);
                }
            }
        });


        cb_paid_filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterFragment.addFree(false);
                } else {
                    filterFragment.removeFree(false);
                }
            }
        });

        cb_beginner_filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterFragment.addLevel(Beginner);
                } else {
                    filterFragment.removeLevel(Beginner);
                }
            }
        });

        cb_intermediate_filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterFragment.addLevel(Intermediate);
                } else {
                    filterFragment.removeLevel(Intermediate);
                }
            }
        });

        cb_all_levels_filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterFragment.addLevel(AllLevel);
                } else {
                    filterFragment.removeLevel(AllLevel);
                }

            }
        });

        cb_expert_filter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterFragment.addLevel(Expert);
                } else {
                    filterFragment.removeLevel(Expert);
                }
            }
        });
    }

    private void resetSearch() {
        filterFragment.rating = null;
        filterFragment.frees = new ArrayList<>();
        filterFragment.levels = new ArrayList<>();
        filterFragment.setCourses();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (filterFragment.frees.contains(true)) {
            cb_free_filter.setChecked(true);
        } else {
            cb_free_filter.setChecked(false);
        }

        if (filterFragment.frees.contains(false)) {
            cb_free_filter.setChecked(true);
        }else {
            cb_free_filter.setChecked(false);
        }


        if (filterFragment.levels.contains(Beginner)) {
            cb_beginner_filter.setChecked(true);
        }else {
            cb_beginner_filter.setChecked(false);
        }

        if (filterFragment.levels.contains(Intermediate)) {
            cb_intermediate_filter.setChecked(true);
        }else {
            cb_intermediate_filter.setChecked(false);
        }


        if (filterFragment.levels.contains(AllLevel)) {
            cb_all_levels_filter.setChecked(true);
        }else {
            cb_all_levels_filter.setChecked(false);
        }


        if (filterFragment.levels.contains(Expert)) {
            cb_expert_filter.setChecked(true);
        }else {
            cb_expert_filter.setChecked(false);
        }

        Float currentRating = filterFragment.rating;

        if (currentRating != null) {
            if (currentRating.equals(4.5f)) {
                rb_45_filter.setChecked(true);
            } else if (currentRating.equals(4.0f)) {
                rb_40_filter.setChecked(true);

            } else if (currentRating.equals(3.5f)) {
                rb_35_filter.setChecked(true);

            } else if (currentRating.equals(3.0f)) {
                rb_30_filter.setChecked(true);
            }
        }



    }

    private void setControl(View rootView) {
        cb_free_filter = rootView.findViewById(R.id.cb_free_filter);
        cb_paid_filter = rootView.findViewById(R.id.cb_paid_filter);
        cb_beginner_filter = rootView.findViewById(R.id.cb_beginner_filter);
        cb_all_levels_filter = rootView.findViewById(R.id.cb_all_levels_filter);
        cb_expert_filter = rootView.findViewById(R.id.cb_expert_filter);
        cb_intermediate_filter = rootView.findViewById(R.id.cb_intermediate_filter);
        btn_reset_filter = rootView.findViewById(R.id.btn_reset_filter);
        iv_dismiss_filter = rootView.findViewById(R.id.iv_dismiss_filter);
        btn_apply_filter = rootView.findViewById(R.id.btn_apply_filter);
        rg_ratings = rootView.findViewById(R.id.rg_ratings);
        rb_45_filter = rootView.findViewById(R.id.rb_45_filter);
        rb_40_filter = rootView.findViewById(R.id.rb_40_filter);
        rb_35_filter = rootView.findViewById(R.id.rb_35_filter);
        rb_30_filter = rootView.findViewById(R.id.rb_30_filter);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        LinearLayout layout = dialog.findViewById(R.id.ll_bottomSheet);

        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
    }
}
