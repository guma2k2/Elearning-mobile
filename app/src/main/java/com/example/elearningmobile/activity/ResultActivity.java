package com.example.elearningmobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.elearningmobile.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        setContentView(webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                handlePaymentCallback(url); // Process payment result
                return true;
            }
        });

        try {
            String paymentUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_Amount=10000000&vnp_BankCode=NCB&vnp_Command=pay&vnp_CreateDate=20241106213900&vnp_CurrCode=VND&vnp_ExpireDate=20241106215400&vnp_IpAddr=0%3A0%3A0%3A0%3A0%3A0%3A0%3A1&vnp_Locale=vn&vnp_OrderInfo=1222&vnp_OrderType=bill&vnp_ReturnUrl=http%3A%2F%2Flocalhost%3A5173%2Fvn-pay-callback&vnp_TmnCode=EDT2E52Y&vnp_TxnRef=12834289&vnp_Version=2.1.0&vnp_SecureHash=d6566499969ecbc6350ab6b9e2346ac555c77724c6015056ea691c8d285e916cb89d09d3d7f05d71bdfca413253b68f90885f41ea84219468c578e6d94021590";
            webView.loadUrl(paymentUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePaymentCallback(String url) {
        // Extract parameters from the URL to determine the payment status
    }
}