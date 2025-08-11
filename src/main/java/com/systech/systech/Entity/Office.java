package com.systech.systech.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "office")
public class Office extends BaseEntity {

    @Column(nullable = false)
    private String officeCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private String postalCode;

    @Column
    private String territory;



}
