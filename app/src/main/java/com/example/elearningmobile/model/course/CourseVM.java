package com.example.elearningmobile.model.course;

import com.example.elearningmobile.model.section.SectionVM;
import com.example.elearningmobile.model.user.UserProfileVM;

import java.util.List;

public class CourseVM {
    private Long id;
    private String title;
    private String headline;
    private String slug;
    private String[] objectives;
    private String[] requirements;
    private String[] targetAudiences;
    private String description;
    private String level;
    private String image;
    private String createdAt;
    private String updatedAt;
    private boolean free;
    private Long price;
    private boolean isPublish;
    private Integer categoryId;
    private Integer topicId;
    private int ratingCount;
    private double averageRating;
    private int totalLectureCourse;
    private String totalDurationCourse;
    private String createdBy;
    private List<SectionVM> sections;
    private UserProfileVM user;
    private boolean learning;
    private String breadcrumb;

}
