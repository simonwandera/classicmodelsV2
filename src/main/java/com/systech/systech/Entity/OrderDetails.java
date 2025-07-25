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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@ToString(exclude = {"order", "product"}) // Exclude to avoid circular references
public class OrderDetails extends BaseEntity {


    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private Integer quantityOrdered;

    @Column(nullable = false)
    private String priceEach;

    @Column(nullable = false)
    private String orderLineNumber;

    // Many-to-One relationship with Orders
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")//references the ID, not orderNumber
    private Orders order;

    // Many-to-One relationship with Products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code_id")
    private Product product;
}

