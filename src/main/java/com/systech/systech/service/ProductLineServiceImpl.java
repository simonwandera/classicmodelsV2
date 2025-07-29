package com.systech.systech.service;

import com.systech.systech.Entity.ProductLine;
import com.systech.systech.Repository.ProductLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductLineServiceImpl implements ProductLineService {

    private final ProductLineRepository productLineRepository;

    @Override
    public List<ProductLine> getProductList() {
        log.info("Fetching all product lines");
        return productLineRepository.findAll();
    }

    @Override
    public ProductLine createOrUpdate(ProductLine productLine) {
        log.info("Creating or updating product line: {}", productLine.getProductCode());
        return productLineRepository.save(productLine);
    }

    // Additional methods you might need:
    public Optional<ProductLine> getById(Long id) {
        log.info("Fetching product line by ID: {}", id);
        return productLineRepository.findById(id);
    }

    public boolean delete(Long id) {
        log.info("Deleting product line with ID: {}", id);
        if (productLineRepository.existsById(id)) {
            productLineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}