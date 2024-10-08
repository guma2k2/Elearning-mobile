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
}
