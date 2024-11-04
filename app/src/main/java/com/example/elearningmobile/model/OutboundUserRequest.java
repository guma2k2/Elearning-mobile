package com.example.elearningmobile.model;

public class OutboundUserRequest {
    private String email;
    private String givenName;
    private String familyName;
    private String picture;

    public OutboundUserRequest() {
    }

    public OutboundUserRequest(String email, String givenName, String familyName, String picture) {
        this.email = email;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
