package com.example.elearningmobile.model.order;

import java.io.Serializable;
import java.util.List;

public class OrderPostDto implements Serializable {
    private List<OrderDetailPostDto> orderDetails;

    public OrderPostDto() {
    }

    public OrderPostDto(List<OrderDetailPostDto> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<OrderDetailPostDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailPostDto> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
