package com.systech.systech.service;

import com.systech.systech.Entity.ProductLine;
import com.systech.systech.Repository.ProductLineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductLineServiceImpl implements ProductLineService {
    private final ProductLineRepository productLineRepository;

    @Override
    public ProductLine createProductLine(ProductLine productLine) {
        return productLineRepository.save(productLine);
    }

    @Override
    public ProductLine updateProductLine(Long id, ProductLine productLine) {
        ProductLine existing = productLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product line not found"));
        existing.setProductLine(productLine.getProductLine());
        existing.setTextDescription(productLine.getTextDescription());
        existing.setHtmlDescription(productLine.getHtmlDescription());
        existing.setImage(productLine.getImage());
        return productLineRepository.save(existing);
    }

    @Override
    public List<ProductLine> getAllProductLines() {
        return productLineRepository.findAll();
    }

    @Override
    public ProductLine getProductLineById(Long id) {
        return productLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product line not found"));
    }

    @Override
    public void deleteProductLine(Long id) {
        if (!productLineRepository.existsById(id)) {
            throw new RuntimeException("Product line not found");
        }
        productLineRepository.deleteById(id);
    }
}