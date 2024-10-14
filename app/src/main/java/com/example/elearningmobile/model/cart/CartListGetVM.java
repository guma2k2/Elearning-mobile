package com.example.elearningmobile.model.cart;

import com.example.elearningmobile.model.course.CourseListGetVM;

public class CartListGetVM {
    private Long id;
    private CourseListGetVM course;
    private boolean buyLater;

    public CartListGetVM(Long id, CourseListGetVM course, boolean buyLater) {
        this.id = id;
        this.course = course;
        this.buyLater = buyLater;
    }

    public CartListGetVM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseListGetVM getCourse() {
        return course;
    }

    public void setCourse(CourseListGetVM course) {
        this.course = course;
    }

    public boolean isBuyLater() {
        return buyLater;
    }

    public void setBuyLater(boolean buyLater) {
        this.buyLater = buyLater;
    }
}
