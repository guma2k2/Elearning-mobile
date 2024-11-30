package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elearningmobile.R;
import com.example.elearningmobile.model.order.OrderVM;

public class ResultActivity extends AppCompatActivity {

    ImageView iv_statusIcon;
    TextView tv_statusText;
    Button btn_returnHome_result;

    Integer status;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setControl();
        setEvent();
    }

    private void setEvent() {
        if (status != null) {
            if (status.equals(200)) {
                iv_statusIcon.setImageResource(R.drawable.ic_success);
            } else if (status.equals(400)) {
                iv_statusIcon.setImageResource(R.drawable.ic_failure);
            }

            tv_statusText.setText(message);
        }
    }

    private void setControl() {
        iv_statusIcon = findViewById(R.id.iv_statusIcon);
        tv_statusText = findViewById(R.id.tv_statusText);
        btn_returnHome_result = findViewById(R.id.btn_returnHome_result);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getInt("status");
            message = extras.getString("message");
        }
    }


}