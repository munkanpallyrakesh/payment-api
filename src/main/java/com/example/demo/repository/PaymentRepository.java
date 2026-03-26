package com.example.demo.repository;

import com.example.demo.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    List<PaymentEntity> findByCurrencyIn(List<String> currencies);

    List<PaymentEntity> findByAmountGreaterThanEqual(BigDecimal amount);

    List<PaymentEntity> findByCurrencyInAndAmountGreaterThanEqual(List<String> currencies, BigDecimal amount);
}