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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "customer_number", nullable = false)
        private String customerNumber;

        @Column(nullable = false)
        private String checkNumber;

        @Column(nullable = false)
        private LocalDate paymentDate;

        @Column(nullable = false, precision = 10, scale = 2)
        private BigDecimal amount;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_number", referencedColumnName = "customerNumber", nullable = false, insertable = false, updatable = false)
        private Customer customer;
    }
