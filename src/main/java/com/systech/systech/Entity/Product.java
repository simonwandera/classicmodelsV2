package com.systech.systech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"productLine", "orderDetails"}) // Exclude to avoid circular references
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productCode;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productScale;

    @Column(nullable = false)
    private String productVendor;

    @Column(columnDefinition = "TEXT")
    private String productDescription;

    @Column(nullable = false)
    private Integer quantityInStock;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal buyPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal msrp; // Manufacturer's Suggested Retail Price

    // Many-to-One relationship with ProductLine
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_line_id", nullable = false)
    private ProductLine productLine;

    // One-to-Many relationship with OrderDetails
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;
}