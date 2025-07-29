package com.systech.systech.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

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

    @Column(name = "payment_date", nullable = false)
    private String paymentDate;

    @Column
    private String amount;



}