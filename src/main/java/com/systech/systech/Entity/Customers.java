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

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers extends BaseEntity{

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @Column(name = "customer_name",nullable = false)
    private String customerName;

    @Column(name = "contact_last_name", nullable = false)
    private String contactLastName;

    @Column(name = "contact_first_name", nullable = false)
    private String contactFirstName;

    @Column(nullable = false)
    private String phone;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column
    private String state;

    @Column (name = "postal_code")
    private String postalCode;

    @Column
    private String country;

    @Column
    private Double creditLimit;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id", nullable = false)
    private Employee salesRep;

    // One-to-Many relationship with Orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;

    // One-to-Many relationship with Payments
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payments> payments;
}
