package com.example.elearningmobile.model.order;

import java.io.Serializable;
import java.util.List;

public class OrderVM implements Serializable {
    private Long id;
    private String student;
    private String coupon;
    private String createdAt;
    private String status;
    private List<OrderDetailVM> orderDetails;
    private Long totalPrice;

    public OrderVM() {
    }

    public OrderVM(Long id, String student, String coupon, String createdAt, String status, List<OrderDetailVM> orderDetails, Long totalPrice) {
        this.id = id;
        this.student = student;
        this.coupon = coupon;
        this.createdAt = createdAt;
        this.status = status;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetailVM> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailVM> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
