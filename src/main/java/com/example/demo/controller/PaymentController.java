package com.example.demo.controller;

import com.example.api.PaymentsApi;
import com.example.demo.service.PaymentService;
import org.openapitools.model.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PaymentController implements PaymentsApi {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Payment> paymentsPost(Payment payment) {
        Payment saved = service.createPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<Payment>> paymentsGet(BigDecimal minAmount, List<String> currencies) {
        List<Payment> payments = service.getPayments(minAmount, currencies);
        return ResponseEntity.ok(payments);
    }
}
