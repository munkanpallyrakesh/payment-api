package com.example.demo.controller;


import com.example.demo.model.Payment;
import com.example.demo.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public Payment create(@Valid @RequestBody Payment payment) {
        return service.createPayment(payment);
    }

    @GetMapping
    public List<Payment> getPayments(
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) String currencies) {

        List<String> currencyList = null;

        if (currencies != null) {
            currencyList = Arrays.asList(currencies.split(","));
        }

        return service.getPayments(minAmount, currencyList);
    }
}
