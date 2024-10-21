package com.example.elearningmobile.model.user;

import com.example.elearningmobile.model.course.CourseListGetVM;

import java.util.List;

public class UserProfileVM {
    private Long id;
    private String fullName;
    private String photo;
    private Double averageRating;
    private int numberOfReview;
    private int numberOfStudent;
    private int numberOfCourse;
    private List<CourseListGetVM> courses;

    public UserProfileVM() {
    }

    public UserProfileVM(Long id, String fullName, String photo, Double averageRating, int numberOfReview, int numberOfStudent, int numberOfCourse, List<CourseListGetVM> courses) {
        this.id = id;
        this.fullName = fullName;
        this.photo = photo;
        this.averageRating = averageRating;
        this.numberOfReview = numberOfReview;
        this.numberOfStudent = numberOfStudent;
        this.numberOfCourse = numberOfCourse;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(int numberOfReview) {
        this.numberOfReview = numberOfReview;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public int getNumberOfCourse() {
        return numberOfCourse;
    }

    public void setNumberOfCourse(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }

    public List<CourseListGetVM> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseListGetVM> courses) {
        this.courses = courses;
    }
}
