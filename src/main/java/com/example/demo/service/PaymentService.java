package com.example.demo.service;

import com.example.demo.entity.PaymentEntity;
import com.example.demo.repository.PaymentRepository;
import org.openapitools.model.Account;
import org.openapitools.model.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        log.info("Creating payment: currency={}, amount={}", payment.getCurrency(), payment.getAmount());

        PaymentEntity entity = convertToEntity(payment);
        PaymentEntity saved = repo.save(entity);

        log.info("Payment created with id={}", saved.getId());
        return convertToDto(saved);
    }

    @Transactional(readOnly = true)
    public List<Payment> getPayments(BigDecimal minAmount, List<String> currencies) {
        log.info("Fetching payments: minAmount={}, currencies={}", minAmount, currencies);

        List<PaymentEntity> entities;

        if (minAmount != null && currencies != null) {
            entities = repo.findByCurrencyInAndAmountGreaterThanEqual(currencies, minAmount);
        } else if (minAmount != null) {
            entities = repo.findByAmountGreaterThanEqual(minAmount);
        } else if (currencies != null) {
            entities = repo.findByCurrencyIn(currencies);
        } else {
            entities = repo.findAll();
        }

        log.info("Found {} payments", entities.size());
        return entities.stream().map(this::convertToDto).toList();
    }

    private PaymentEntity convertToEntity(Payment payment) {
        PaymentEntity entity = new PaymentEntity();

        entity.setCurrency(payment.getCurrency());
        entity.setAmount(payment.getAmount());

        if (payment.getCounterparty() != null) {
            entity.setAccountNumber(payment.getCounterparty().getAccountNumber());
            entity.setSortCode(payment.getCounterparty().getSortCode());
            if (payment.getCounterparty().getType() != null) {
                entity.setType(payment.getCounterparty().getType().name());
            } else {
                log.warn("Payment counterparty type is null for currency={}", payment.getCurrency());
            }
        }

        return entity;
    }

    private Payment convertToDto(PaymentEntity entity) {
        Payment payment = new Payment();

        payment.setCurrency(entity.getCurrency());
        payment.setAmount(entity.getAmount());

        Account account = new Account();
        account.setAccountNumber(entity.getAccountNumber());
        account.setSortCode(entity.getSortCode());
        if (entity.getType() != null) {
            account.setType(Account.TypeEnum.valueOf(entity.getType()));
        }

        payment.setCounterparty(account);

        return payment;
    }
}
