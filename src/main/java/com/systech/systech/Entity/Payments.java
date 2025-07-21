package com.systech.systech.Entity;

    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;
    import lombok.ToString;

    import java.math.BigDecimal;
    import java.time.LocalDate;

    @Entity
    @Table(name = "payments")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString(exclude = {"customer"})



public class Payments {
    @Id
    @Column(name = "checkNumber")
    private String check_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_number", nullable = false) // foreign key column
    private Customers customer; // reference to Customer entity

    @Column
    private String pmt_date;

    @Column
    private String amount;



}
