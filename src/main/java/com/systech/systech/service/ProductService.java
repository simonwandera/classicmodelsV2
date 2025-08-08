package com.systech.systech.service;

import com.systech.systech.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAll();
    Page<Product> getAll(Pageable pageable); // New paginated method

    Optional<Product> getById(Long id);

    Optional<Product> getByProductCode(String productCode);

    Product create(Product product);

    Optional<Product> update(Long id, Product updatedProduct);

    boolean delete(Long id);

    String getMostSoldProduct();

    // New methods for frontend requirements
    Page<Product> findByCategory(String category, Pageable pageable);
    Page<Product> findNewArrivals(Pageable pageable);
    Page<Product> findBestSellers(Pageable pageable);
    Page<Product> searchProducts(String query, Pageable pageable);

}