package com.systech.systech.service;

import com.systech.systech.Entity.ProductLine;

import java.util.List;


public interface ProductLineService {
    List<ProductLine> getProductList();

    ProductLine createOrUpdate(ProductLine productLine);
}
