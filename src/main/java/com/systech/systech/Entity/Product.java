
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
    @Column(name = "productCode")
    private String productCode;

    @Column(nullable = false)
    private String product_code;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(nullable = false)
    private String MSRP;


}
