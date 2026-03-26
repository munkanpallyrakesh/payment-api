package com.example.demo.entity;

import org.openapitools.model.Payment;

import java.util.List;

public class PaymentsResponse {
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private List<Payment> payments;
    private String message;
}
