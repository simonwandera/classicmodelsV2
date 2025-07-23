package com.systech.systech.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "products")//I think the table name here is supposed to be products
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productCode")
    private String productCode;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String MSRP; // Manufacturer's Suggested Retail Price

    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "product_line_id")
    private ProductLine product_line;

    @Column(nullable = false)
    private String product_scale;

    @Column(nullable = false)
    private String product_vendor;

    @Column(nullable = false)
    private String product_description;

    @Column(nullable = false)
    private String quantity_in_stock;

    @Column(nullable = false)
    private String buy_price;





    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <OrderDetails> orderDetails;
}

