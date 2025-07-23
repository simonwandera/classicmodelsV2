package com.systech.systech.controller;

import com.systech.systech.Entity.Payments;
import com.systech.systech.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PaymentsController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payments>> getAll() {
        log.info("GET /api/payments");
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payments> getById(@PathVariable Long id) {
        log.info("GET /api/payments/{}", id);
        return paymentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Payments>> getByCustomer(@PathVariable Long customerId) {
        log.info("GET /api/payments/customer/{}", customerId);
        return ResponseEntity.ok(paymentService.getByCustomer(customerId));
    }

    @GetMapping("/search/{checkNumber}")
    public ResponseEntity<List<Payments>> searchByCheckNumber(@PathVariable String checkNumber) {
        log.info("GET /api/payments/search/{}", checkNumber);
        return ResponseEntity.ok(paymentService.searchByCheckNumber(checkNumber));
    }

    @PostMapping
    public ResponseEntity<Payments> create(@Validated @RequestBody Payments payment) {
        log.info("POST /api/payments");
        return ResponseEntity.ok(paymentService.create(payment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payments> update(@PathVariable Long id, @Validated @RequestBody Payments payment) {
        log.info("PUT /api/payments/{}", id);
        return paymentService.update(id, payment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/payments/{}", id);
        return paymentService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
