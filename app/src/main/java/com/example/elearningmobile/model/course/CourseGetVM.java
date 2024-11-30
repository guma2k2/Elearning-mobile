package com.example.elearningmobile.model.course;

import java.io.Serializable;

public class CourseGetVM implements Serializable {
    private Long id;
    private String title;
    private String headline;
    private String description;
    private String level;
    private String image;

    public CourseGetVM() {
    }

    public CourseGetVM(Long id, String title, String headline, String description, String level, String image) {
        this.id = id;
        this.title = title;
        this.headline = headline;
        this.description = description;
        this.level = level;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
