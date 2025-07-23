package com.systech.systech.service;

import com.systech.systech.Entity.Payments;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payments> getAll();

    Optional<Payments> getById(Long id);

    List<Payments> getByCustomer(Long customerId);

    List<Payments> searchByCheckNumber(String partialCheckNumber);

    Payments create(Payments payment);

    Optional<Payments> update(Long id, Payments updatedPayment);

    boolean delete(Long id);
}
