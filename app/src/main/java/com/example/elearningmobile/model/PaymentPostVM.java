package com.example.elearningmobile.model;

import java.io.Serializable;

public class PaymentPostVM implements Serializable {

    private long amount;
    private String bankCode;
    private String bankTranNo;
    private String cardType;
    private String payDate;
    private Long orderId;

    public PaymentPostVM() {
    }

    public PaymentPostVM(long amount, String bankCode, String bankTranNo, String cardType, String payDate, Long orderId) {
        this.amount = amount;
        this.bankCode = bankCode;
        this.bankTranNo = bankTranNo;
        this.cardType = cardType;
        this.payDate = payDate;
        this.orderId = orderId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankTranNo() {
        return bankTranNo;
    }

    public void setBankTranNo(String bankTranNo) {
        this.bankTranNo = bankTranNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
