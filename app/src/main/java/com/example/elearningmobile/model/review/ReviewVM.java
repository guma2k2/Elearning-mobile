package com.example.elearningmobile.model.review;

import com.example.elearningmobile.model.user.UserGetVM;

public class ReviewVM {
    private Long id;
    private String content;
    private int ratingStar;
    private UserGetVM student;
    private String createdAt; // Java naming conventions prefer camel case
    private String updatedAt;
    private boolean status;

    // Constructor
    public ReviewVM(Long id, String content, int ratingStar, UserGetVM student, String createdAt, String updatedAt, boolean status) {
        this.id = id;
        this.content = content;
        this.ratingStar = ratingStar;
        this.student = student;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getRatingStar() {
        return ratingStar;
    }

    public UserGetVM getStudent() {
        return student;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isStatus() {
        return status;
    }

    // Optionally, you can add setters if needed (for a mutable class)
    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRatingStar(int ratingStar) {
        this.ratingStar = ratingStar;
    }

    public void setStudent(UserGetVM student) {
        this.student = student;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
