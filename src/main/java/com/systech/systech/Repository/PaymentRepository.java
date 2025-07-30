package com.systech.systech.Repository;

import com.systech.systech.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomer_Id(Long customerId);

    List<Payment> findByCheckNumberContainingIgnoreCase(String partialCheckNumber);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentDate BETWEEN :formDate and :toDate GROUP BY p.amount")
    Optional<Long> getTotalSalesByYear(LocalDateTime formDate, LocalDateTime toDate);
}
