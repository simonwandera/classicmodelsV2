package com.systech.systech.service;

import com.systech.systech.Entity.Payment;
import com.systech.systech.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        log.info("Fetching all payments");
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getById(Long id) {
        log.info("Fetching payment by ID: {}", id);
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getByCustomer(Long customerId) {
        log.info("Fetching payments for customer ID: {}", customerId);
        return paymentRepository.findByCustomer_Id(customerId);
    }

    @Override
    public List<Payment> searchByCheckNumber(String partialCheckNumber) {
        log.info("Searching payments by partial check number: {}", partialCheckNumber);
        return paymentRepository.findByCheckNumberContainingIgnoreCase(partialCheckNumber);
    }

    @Override
    public Payment create(Payment payment) {
        log.info("Creating payment: check #{} for customer ID {}", payment.getCheckNumber(), payment.getCustomer().getId());
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> update(Long id, Payment updatedPayment) {
        log.info("Updating payment with ID: {}", id);
        return paymentRepository.findById(id).map(existing -> {
            updatedPayment.setId(id);
            return paymentRepository.save(updatedPayment);
        });
    }

    @Override
    public boolean delete(Long id) {
        log.info("Deleting payment with ID: {}", id);
        return paymentRepository.findById(id).map(existing -> {
            paymentRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
