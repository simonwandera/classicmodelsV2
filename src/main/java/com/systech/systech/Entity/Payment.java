package com.systech.systech.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"customer"})

public class Payment extends BaseEntity {

    @Column(name = "check_number")
    private String checkNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_number", nullable = false) // foreign key column
    private Customer customer;

    @Column(name = "pmt_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column
    private BigDecimal amount;
}