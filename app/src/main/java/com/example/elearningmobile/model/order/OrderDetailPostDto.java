package com.example.elearningmobile.model.order;

import java.io.Serializable;

public class OrderDetailPostDto implements Serializable {

    private Long courseId;
    private Long price;

    public OrderDetailPostDto(Long courseId, Long price) {
        this.courseId = courseId;
        this.price = price;
    }

    public OrderDetailPostDto() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
