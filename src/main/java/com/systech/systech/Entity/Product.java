package com.systech.systech.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "product")//I think the table name here is supposed to be products
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{

    @Column(name = "productCode")
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName; // Changed from productName to match frontend

    @Column(name = "price", nullable = false)
    private Double MSRP; // Changed from PRICE to match frontend

    @Column(name = "original_price")
    private Double originalPrice; // New field for frontend

    @Column(name = "category")
    private String category; // New field for frontend

    // New field for frontend

    @Column(name = "rating")
    private Double rating; // New field for frontend

    @Column(name = "is_new")
    private Boolean isNew; // New field for frontend

    @Column(name = "is_best_seller")
    private Boolean isBestSeller; // New field for frontend

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock; // Changed from quantity_in_stock to match frontend

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_line_id")
    private ProductLine productLine;

    @Column(name = "product_scale")
    private String productScale; // Changed from product_scale

    @Column(name = "product_vendor")
    private String productVendor; // Changed from product_vendor

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription; // Changed from product_description

    @Column(name = "buy_price")
    private Double buyPrice; // Changed from buy_price

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}

