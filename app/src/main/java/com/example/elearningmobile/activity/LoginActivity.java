package com.example.elearningmobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elearningmobile.R;
import com.example.elearningmobile.api.AuthApi;
import com.example.elearningmobile.model.AuthenticationPostVm;
import com.example.elearningmobile.model.AuthenticationVm;
import com.example.elearningmobile.variable.GlobalVariable;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.jetbrains.annotations.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText ed_email_login, ed_password_login;
    Button btn_login, btn_register_home, btn_loginGoogle;

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();
    }

    private void setEvent() {



        btn_loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oneTapClient.beginSignIn(signInRequest).addOnSuccessListener(new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult beginSignInResult) {
                        try {
                            startIntentSenderForResult(
                                    beginSignInResult.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
                                    null, 0, 0, 0);
                        } catch (IntentSender.SendIntentException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }) ;
            }
        });
        btn_register_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToRegisterPage();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email_login.getText().toString().trim();
                String password = ed_password_login.getText().toString().trim();
                AuthenticationPostVm authenticationPostVm = new AuthenticationPostVm(email, password);
                AuthApi.authApi.login(authenticationPostVm).enqueue(new Callback<AuthenticationVm>() {
                    @Override
                    public void onResponse(Call<AuthenticationVm> call, Response<AuthenticationVm> response) {
                        AuthenticationVm authenticationResponse = response.body();
                        GlobalVariable globalVariable = (GlobalVariable) getApplication();
                        globalVariable.setAuthentication(authenticationResponse);
                        globalVariable.setLoggedIn(true);
                        Toast.makeText(getApplicationContext(),  "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        redirectToHomePage();
                    }

                    @Override
                    public void onFailure(Call<AuthenticationVm> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra trong quá trình đăng nhap", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }


    private void redirectToHomePage() {
        Intent activityChangeIntent = new Intent(this, MainActivity.class);
        this.startActivity(activityChangeIntent);
    }

    private void redirectToRegisterPage() {
        Intent activityChangeIntent = new Intent(this, RegisterActivity.class);
        this.startActivity(activityChangeIntent);
    }
    private void setControl() {
        ed_email_login = findViewById(R.id.ed_email_login);
        ed_password_login = findViewById(R.id.ed_password_login);
        btn_login = findViewById(R.id.btn_login);
        btn_register_home = findViewById(R.id.btn_register_home);

        btn_loginGoogle = findViewById(R.id.btn_loginGoogle);

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId("553874834892-51tqjh0j2m0nren6789bj02h6t2bnquh.apps.googleusercontent.com") // TODO
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    String username = credential.getId();
                    String password = credential.getPassword();
//                    textView.setText("Authentication done.\nUsername is " + username);
                    if (idToken != null) {
                        // Got an ID token from Google. Use it to authenticate
                        // with your backend.
                    } else if (password != null) {
                        // Got a saved username and password. Use them to authenticate
                        // with your backend.
                    }
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}