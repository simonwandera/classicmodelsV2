package com.systech.systech.Repository;

import com.systech.systech.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {





    List<Payment> findByCustomer_Id(Long customerId);

    List<Payment> findByCheckNumberContainingIgnoreCase(String partialCheckNumber);
}
