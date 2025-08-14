package com.systech.systech.service;

import com.systech.systech.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    // Basic CRUD operations
    List<Product> getAll();
    Page<Product> getAll(Pageable pageable);
    Optional<Product> getById(Long id);
    Optional<Product> getByProductCode(String productCode);
    Product create(Product product);
    Optional<Product> update(Long id, Product product);
    boolean delete(Long id);

    // Business logic methods
    String getMostSoldProduct();

    // Frontend-specific queries
    Page<Product> findByCategory(String category, Pageable pageable);
    Page<Product> findNewArrivals(Pageable pageable);
    Page<Product> findBestSellers(Pageable pageable);
    Page<Product> searchProducts(String query, Pageable pageable);

    // Additional business methods
    List<Product> findLowStockProducts(int threshold);
    List<Product> findByPriceRange(Double minPrice, Double maxPrice);
    List<Product> findDiscountedProducts();
}