package com.systech.systech.Dto;

import com.systech.systech.Entity.Product;
import lombok.Getter;

@Getter
public class ProductSalesDTO {
    private final Product product;
    private final Long productId;
    private final String productName;
    private final String productCode;
    private final Double price;
    private final String imageUrl;
    private final Long totalSold;

    // Constructor using the full Product entity
    public ProductSalesDTO(Product product, Long totalSold) {
        this.product = product;
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.productCode = product.getProductCode();
        this.price = product.getMSRP();
        this.imageUrl = product.getProductLine() != null ?
                product.getProductLine().getImage() : null;
        this.totalSold = totalSold;
    }

    // Constructor for JPQL projection (without the full Product object)
    public ProductSalesDTO(Long productId, String productName, String productCode,
                           Double price, String imageUrl, Long totalSold) {
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
        this.imageUrl = imageUrl;
        this.totalSold = totalSold;
        this.product = null; // explicitly set to null since it's not available
    }
}
