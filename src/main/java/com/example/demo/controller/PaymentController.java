package com.example.demo.controller;


import com.example.demo.entity.PaymentsResponse;
import com.example.demo.service.PaymentService;
import jakarta.validation.Valid;
import org.openapitools.model.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Payment> create(@Valid @RequestBody Payment payment) {
        Payment saved = service.createPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }



    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            return Map.of(
                    "message", "Bad Request",
                    "errors", errors
            );
        }
    }

    @GetMapping
    public PaymentsResponse getPayments(
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) String currencies) {

        List<String> currencyList = null;

        if (currencies != null) {
            currencyList = Arrays.asList(currencies.split(","));
        }

        return service.getPayments(minAmount, currencyList);
    }
}
