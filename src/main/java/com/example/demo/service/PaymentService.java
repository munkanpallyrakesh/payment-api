package com.example.demo.service;

import com.example.demo.entity.PaymentEntity;
import com.example.demo.entity.PaymentsResponse;
import com.example.demo.repository.PaymentRepository;
import org.openapitools.model.Account;
import org.openapitools.model.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    // ✅ CREATE PAYMENT
    public Payment createPayment(Payment payment) {

        PaymentEntity entity = convertToEntity(payment);
        PaymentEntity saved = repo.save(entity);

        return convertToDto(saved);
    }

    public PaymentsResponse getPayments(Double minAmount, List<String> currencies) {

        List<PaymentEntity> entities;
        BigDecimal bdAmount = minAmount != null ? BigDecimal.valueOf(minAmount) : null;

        if (minAmount != null && currencies != null) {
            entities = repo.findByCurrencyInAndAmountGreaterThanEqual(currencies, bdAmount);

        } else if (minAmount != null) {
            entities = repo.findByAmountGreaterThanEqual(bdAmount);

        } else if (currencies != null) {
            entities = repo.findByCurrencyIn(currencies);

        } else {
            entities = repo.findAll();
        }

        PaymentsResponse response = new PaymentsResponse();

        if (entities.isEmpty()) {
            response.setPayments(List.of());
            response.setMessage("NoPaymentsFound");
        } else {
            response.setPayments(
                    entities.stream().map(this::convertToDto).toList()
            );
            response.setMessage("Payments");
        }

        return response;
    }
    // ✅ DTO → ENTITY
    private PaymentEntity convertToEntity(Payment payment) {
        PaymentEntity entity = new PaymentEntity();

        entity.setCurrency(payment.getCurrency());
        entity.setAmount(payment.getAmount());

        if (payment.getCounterparty() != null) {
            entity.setAccountNumber(payment.getCounterparty().getAccountNumber());
            entity.setSortCode(payment.getCounterparty().getSortCode());
            entity.setType(payment.getCounterparty().getType().name());
        }

        return entity;
    }

    // ✅ ENTITY → DTO
    private Payment convertToDto(PaymentEntity entity) {
        Payment payment = new Payment();

        payment.setCurrency(entity.getCurrency());

        // ✅ FIXED LINE
        payment.setAmount(entity.getAmount());

        Account account = new Account();

        account.setAccountNumber(entity.getAccountNumber());
        account.setSortCode(entity.getSortCode());
        account.setType(Account.TypeEnum.valueOf(entity.getType()));

        payment.setCounterparty(account);

        return payment;
    }
}