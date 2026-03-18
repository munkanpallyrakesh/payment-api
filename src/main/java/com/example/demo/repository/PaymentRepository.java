package com.example.demo.repository;

import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByAmountGreaterThanEqual(double amount);

    List<Payment> findByCurrencyIn(List<String> currencies);

    List<Payment> findByCurrencyInAndAmountGreaterThanEqual(List<String> currencies, double amount);
}
