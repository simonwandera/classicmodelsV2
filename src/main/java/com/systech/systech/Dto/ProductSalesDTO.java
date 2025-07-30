package com.systech.systech.Dto;

import com.systech.systech.Entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSalesDTO {
    private Product product;
    private Long totalSold;
}