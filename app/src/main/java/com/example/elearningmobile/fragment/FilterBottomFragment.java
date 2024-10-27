package com.example.elearningmobile.fragment;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elearningmobile.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FilterBottomFragment extends BottomSheetDialogFragment {
    BottomSheetDialog dialog;
    BottomSheetBehavior<View> bottomSheetBehavior ;
    View rootView ;

    CheckBox cb_free_filter, cb_paid_filter, cb_45_filter, cb_40_filter, cb_35_filter, cb_30_filter;
    TextView btn_reset_filter;
    ImageView iv_dismiss_filter;
    Button btn_apply_filter;

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
        return rootView;
    }

    private void setControl(View rootView) {
        cb_free_filter = rootView.findViewById(R.id.cb_free_filter);
        cb_paid_filter = rootView.findViewById(R.id.cb_paid_filter);
        cb_45_filter = rootView.findViewById(R.id.cb_45_filter);
        cb_40_filter = rootView.findViewById(R.id.cb_40_filter);
        cb_35_filter = rootView.findViewById(R.id.cb_35_filter);
        cb_30_filter = rootView.findViewById(R.id.cb_30_filter);
        btn_reset_filter = rootView.findViewById(R.id.btn_reset_filter);
        iv_dismiss_filter = rootView.findViewById(R.id.iv_dismiss_filter);
        btn_apply_filter = rootView.findViewById(R.id.btn_apply_filter);
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
