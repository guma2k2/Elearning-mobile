package com.example.elearningmobile.model;

public class AuthenticationVm {
    private String token;
    private UserVm user;

    public AuthenticationVm(String token, UserVm user) {
        this.token = token;
        this.user = user;
    }

    public AuthenticationVm() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserVm getUser() {
        return user;
    }

    public void setUser(UserVm user) {
        this.user = user;
    }
}
