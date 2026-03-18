package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Account counterparty) {
        this.counterparty = counterparty;
    }

    @NotNull
    private String currency;

    @Min(0)
    private double amount;

    @Embedded
    @Valid
    private Account counterparty;

    // getters & setters
}
