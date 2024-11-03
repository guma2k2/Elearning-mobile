package com.example.elearningmobile.variable;

import android.app.Application;

import com.example.elearningmobile.model.AuthenticationResponse;
import com.example.elearningmobile.model.AuthenticationVm;
import com.example.elearningmobile.model.UserVm;

public class GlobalVariable extends Application {
    private boolean isLoggedIn = false;
    private UserVm auth = null;
    private String access_token ;

    public GlobalVariable() {
    }

    public GlobalVariable(boolean isLoggedIn, UserVm auth, String access_token) {
        this.isLoggedIn = isLoggedIn;
        this.auth = auth;
        this.access_token = access_token;
    }


    public void setAuthentication(AuthenticationVm authenticationResponse) {
        this.isLoggedIn = true;
        this.auth = authenticationResponse.getUser();
        this.access_token = authenticationResponse.getToken();
    }

    public void logOut() {
        this.isLoggedIn = false;
        this.auth = new UserVm();
        this.access_token = "";
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public UserVm getAuth() {
        return auth;
    }

    public void setAuth(UserVm auth) {
        this.auth = auth;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
