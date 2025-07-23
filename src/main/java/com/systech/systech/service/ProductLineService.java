package com.systech.systech.service;

import com.systech.systech.Entity.ProductLine;
import com.systech.systech.Repository.ProductLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductLineService implements ProductLineServiceI {

    private final ProductLineRepository productLineRepository;

    public List<ProductLine> getProductList() {
        return productLineRepository.findAll();
    }

    @Override
    public ProductLine createOrUpdate(ProductLine productLine) {
        return  productLineRepository.save(productLine);
    }
}
