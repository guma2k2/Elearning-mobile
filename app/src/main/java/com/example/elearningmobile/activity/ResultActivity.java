package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        btn_returnHome_result.setOnClickListener(new View.OnClickListener() {
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