package com.example.elearningmobile.model.order;

import com.example.elearningmobile.model.course.CourseGetVM;

import java.io.Serializable;

public class OrderDetailVM implements Serializable {
    private Long id;

    private CourseGetVM course;

    private Long price;

    public OrderDetailVM() {
    }

    public OrderDetailVM(Long id, CourseGetVM course, Long price) {
        this.id = id;
        this.course = course;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseGetVM getCourse() {
        return course;
    }

    public void setCourse(CourseGetVM course) {
        this.course = course;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
