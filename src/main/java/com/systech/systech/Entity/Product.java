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
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{

    @Column(name = "productCode")
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    // Map the database 'price' column to both MSRP and price for frontend compatibility
    @Column(name = "price", nullable = false)
    private Double MSRP;

    // Getter for price to match frontend expectations
    @JsonProperty("price")
    public Double getPrice() {
        return this.MSRP;
    }

    // Setter for price to match frontend expectations
    public void setPrice(Double price) {
        this.MSRP = price;
    }


    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "category")
    private String category;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "is_best_seller")
    private Boolean isBestSeller;

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    // Getter for stock to match frontend expectations
    @JsonProperty("stock")
    public Integer getStock() {
        return this.quantityInStock;
    }

    @ManyToOne(fetch = FetchType.EAGER) // Changed to EAGER to load image
    @JoinColumn(name = "product_line_id")
    private ProductLine productLine;

    @Column(name = "product_scale")
    private String productScale;

    @Column(name = "product_vendor")
    private String productVendor;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "buy_price")
    private Double buyPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    // Helper method to get image from ProductLine with fallback
    @JsonProperty("imageUrl")
    public String getImageUrl() {
        if (this.productLine != null && this.productLine.getImage() != null) {
            return this.productLine.getImage();
        }
        return "/placeholder-product.jpg";
    }

    // For backwards compatibility
    public String getImage() {
        return getImageUrl();
    }
}