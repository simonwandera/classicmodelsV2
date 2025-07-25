package com.systech.systech.service;

import com.systech.systech.Entity.ProductLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductLineServiceI {
    List<ProductLine> getProductList();

    ProductLine createOrUpdate(ProductLine productLine);
}
