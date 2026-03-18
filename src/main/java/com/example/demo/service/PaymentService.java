package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    public Payment createPayment(Payment payment) {
        return repo.save(payment);
    }

    public List<Payment> getPayments(Double minAmount, List<String> currencies) {

        if (minAmount != null && currencies != null) {
            return repo.findByCurrencyInAndAmountGreaterThanEqual(currencies, minAmount);
        } else if (minAmount != null) {
            return repo.findByAmountGreaterThanEqual(minAmount);
        } else if (currencies != null) {
            return repo.findByCurrencyIn(currencies);
        } else {
            return repo.findAll();
        }
    }
}
