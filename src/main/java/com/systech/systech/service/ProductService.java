package com.systech.systech.service;

import com.systech.systech.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();

    Optional<Product> getById(Long id);

    Optional<Product> getByProductCode(String productCode);

    Product create(Product product);

    Optional<Product> update(Long id, Product updatedProduct);

    boolean delete(Long id);
    String generateProductCode();


}
