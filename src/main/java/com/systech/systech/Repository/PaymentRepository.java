package com.systech.systech.Repository;

import com.systech.systech.Entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {





    List<Payments> findByCustomer_Id(Long customerId);

    List<Payments> findByCheckNumberContainingIgnoreCase(String partialCheckNumber);
}
