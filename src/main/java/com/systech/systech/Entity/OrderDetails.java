package com.systech.systech.Entity;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "order_details")
@ToString(exclude = {"order", "product"}) // Exclude to avoid circular references
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private Integer quantityOrdered;

    @Column(nullable = false)
    private String priceEach;

    @Column(nullable = false)
    private Short orderLineNumber;

    // Many-to-One relationship with Orders
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_number", referencedColumnName = "orderNumber")
    private Orders order;

    // Many-to-One relationship with Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "productCode")
    private Product product;
}

