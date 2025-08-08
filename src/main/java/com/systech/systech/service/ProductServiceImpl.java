package com.systech.systech.service;

import com.systech.systech.Entity.Product;
import com.systech.systech.Repository.OrderRepository;
import com.systech.systech.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<Product> getAll() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        log.info("Fetching paginated products (page {}, size {})",
                pageable.getPageNumber(), pageable.getPageSize());
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> getById(Long id) {
        log.info("Fetching product by ID: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getByProductCode(String productCode) {
        log.info("Fetching product by Code: {}", productCode);
        return productRepository.findByProductCode(productCode);
    }

    @Override
    public Product create(Product product) {
        log.info("Creating product: {}", product.getProductName());
        // Set default values for new products
        if (product.getIsNew() == null) product.setIsNew(true);
        if (product.getRating() == null) product.setRating(0.0);
        if (product.getQuantity_in_stock() == null) product.setQuantity_in_stock(0);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product updatedProduct) {
        log.info("Updating product with ID: {}", id);
        return productRepository.findById(id).map(existing -> {
            updatedProduct.setId(id);
            // Preserve critical relationships
            updatedProduct.setProductLine(existing.getProductLine());
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
    public String getMostSoldProduct() {
        Product product = productRepository.findMostSoldProduct()
                .orElse(null);
        return product != null ? product.getProductName() : "None";
    }

    // New methods for frontend requirements

    @Override
    public Page<Product> findByCategory(String category, Pageable pageable) {
        log.info("Fetching products in category: {}", category);
        return productRepository.findByCategory(category, pageable);
    }

    @Override
    public Page<Product> findNewArrivals(Pageable pageable) {
        log.info("Fetching new arrivals");
        return productRepository.findByIsNewTrue(pageable);
    }

    @Override
    public Page<Product> findBestSellers(Pageable pageable) {
        log.info("Fetching best sellers");
        return productRepository.findByIsBestSellerTrue(pageable);
    }

    @Override
    public Page<Product> searchProducts(String query, Pageable pageable) {
        log.info("Searching products for query: {}", query);
        return productRepository.findByProductNameContainingIgnoreCase(query, pageable);
    }
}