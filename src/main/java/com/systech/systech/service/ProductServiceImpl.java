package com.systech.systech.service;

import com.systech.systech.Entity.Product;
import com.systech.systech.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll(){
        log.info("Fetching all products");
        return productRepository.findAll();

    }

    @Override
    public Optional<Product> getById(Long id) {
        log.info("Fetching product by ID: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getByProductCode(String productCode){
        log.info("Fetching product by Code:{}", productCode);
        return productRepository.findByProductCode(productCode);
    }

    @Override
    public Product create(Product product) {
        log.info("Creating product: {}", product.getProductName());
        product.setProductCode(generateProductCode());
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product updatedProduct) {
        log.info("Updating product with ID: {}", id);
        return productRepository.findById(id).map(existing -> {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        });
    }

    @Override
    public boolean delete(Long id) {
        log.info("Deleting product with ID: {}", id);
        return productRepository.findById(id).map(existing -> {
            productRepository.deleteById(id);
            return true;
        }).orElse(false);

    }

    @Override
    public String generateProductCode() {

        int hashCode = UUID.randomUUID().hashCode();

        // Ensure positive and limit to 8 digits
        int refNumber = Math.abs(hashCode) % 100000000;

        // Format with leading zeros
        return String.format("%08d", refNumber);
    }
}
