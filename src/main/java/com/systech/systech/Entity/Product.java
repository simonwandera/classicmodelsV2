
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
@Table(name = "products") //I think the table name here is supposed to be products
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productScale;

    @Column(nullable = false)
    private String productVendor;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private Short quantityInStock;

    @Column(nullable = false)
    private Double buyPrice;

    @Column(nullable = false)
    private Double msrp;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_line_id")
    private ProductLine productLine;

}
