package com.systech.systech.service;

import com.systech.systech.Entity.ProductLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductLineService {
    ProductLine  createProductLine(ProductLine productLine);
    ProductLine updateProductLine(Long id, ProductLine productLine);
    List<ProductLine> getAllProductLines();
    ProductLine getProductLineById(Long id);
    void deleteProductLine(Long id);
}