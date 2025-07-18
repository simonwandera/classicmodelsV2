package com.systech.systech.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")

public class Payments {
    @Id
    @Column(name = "checkNumber")
    private String check_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_number", nullable = false) // foreign key column
    private Customers customer; // reference to Customer entity

    @Column
    private String payment_date;

    @Column
    private String amount;



}
