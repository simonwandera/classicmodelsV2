package com.systech.systech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"orders", "payments", "salesRepEmployee"}) // Exclude to avoid circular references
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String customerNumber;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String contactLastName;

    @Column(nullable = false)
    private String contactFirstName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column
    private String state;

    @Column
    private String postalCode;

    @Column(nullable = false)
    private String country;

    @Column(precision = 10, scale = 2)
    private BigDecimal creditLimit;

    // Many-to-One relationship with Employee (Sales Representative)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_rep_employee_number", referencedColumnName = "employeeNumber")
    private Employee salesRepEmployee;

    // One-to-Many relationship with Orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;

    // One-to-Many relationship with Payments
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payments> payments;
}