package com.systech.systech.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_number;

    @Column
    private String customer_name;

    @Column
    private String contact_last_name;

    @Column
    private String contact_first_name;

    @Column
    private Long phone;

    @Column
    private String address_line_1;

    @Column
    private String address_line_2;

    @Column
    private String city ;

    @Column
    private String state;

    @Column
    private String postal_code;

    @Column
    private String country;

    @Column
    private Long sales_rep_employee_number;

    @Column
    private double credit_limit;

}
