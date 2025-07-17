package com.systech.systech.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String checkNumber;

    @Column(nullable = false)
    private String paymentDate;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customer customerNumber;

}
