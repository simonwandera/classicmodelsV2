package com.systech.systech.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name= "Employee" )

public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long employeeNumber;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String extension;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "officeCode")
    private Office office;

    @ManyToOne
    @JoinColumn(name = "reportsTo")
    private Employee manager;

    // Getters and Setters






}
