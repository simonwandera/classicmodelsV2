package com.systech.systech.service;

import com.systech.systech.Entity.Product;
import com.systech.systech.Entity.ProductLine;
import com.systech.systech.Repository.OrderRepository;
import com.systech.systech.Repository.ProductRepository;
import com.systech.systech.Repository.ProductLineRepository;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductLineRepository productLineRepository; // Add this

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> getAll(Pageable pageable) {
        log.info("Fetching paginated products (page {}, size {})",
                pageable.getPageNumber(), pageable.getPageSize());
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getById(Long id) {
        log.info("Fetching product by ID: {}", id);
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getByProductCode(String productCode) {
        log.info("Fetching product by Code: {}", productCode);
        return productRepository.findByProductCode(productCode);
    }

    @Override
    @Transactional
    public Product create(Product product) {
        log.info("Creating product: {}", product.getProductName());

        // Set default values if null
        if (product.getIsNew() == null) product.setIsNew(true);
        if (product.getRating() == null) product.setRating(0.0);
        if (product.getQuantityInStock() == null) product.setQuantityInStock(0);
        if (product.getIsBestSeller() == null) product.setIsBestSeller(false);

        // Validate unique product code
        productRepository.findByProductCode(product.getProductCode()).ifPresent(existing -> {
            throw new IllegalArgumentException("Product code already exists: " + product.getProductCode());
        });

        // Handle ProductLine assignment if category-based auto-assignment is needed
        if (product.getProductLine() == null && product.getCategory() != null) {
            Optional<ProductLine> productLine = findOrCreateProductLine(product.getCategory());
            product.setProductLine(productLine.orElse(null));
        }

        log.debug("Saving product: {}", product);
        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());

        return savedProduct;
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product updatedProduct) {
        log.info("Updating product with ID: {}", id);

        return productRepository.findById(id).map(existing -> {
            updatedProduct.setId(id); // Ensure the ID is set

            // Preserve critical fields if not provided in update request
            if (updatedProduct.getProductLine() == null) {
                updatedProduct.setProductLine(existing.getProductLine());
            }

            // Preserve audit fields if your BaseEntity has them
            updatedProduct.setCreatedAt(existing.getCreatedAt());

            return productRepository.save(updatedProduct);
        });
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        log.info("Deleting product with ID: {}", id);
        return productRepository.findById(id).map(existing -> {
            productRepository.deleteById(id);
            log.info("Product deleted successfully: {}", id);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional(readOnly = true)
    public String getMostSoldProduct() {
        log.info("Fetching most sold product");
        return productRepository.findMostSoldProduct()
                .map(Product::getProductName)
                .orElse("None");
    }

    // ===== Custom Frontend Queries =====

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findByCategory(String category, Pageable pageable) {
        log.info("Fetching products in category: {}", category);
        return productRepository.findByCategory(category, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findNewArrivals(Pageable pageable) {
        log.info("Fetching new arrivals");
        return productRepository.findByIsNewTrue(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findBestSellers(Pageable pageable) {
        log.info("Fetching best sellers");
        return productRepository.findByIsBestSellerTrue(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> searchProducts(String query, Pageable pageable) {
        log.info("Searching products for query: {}", query);
        return productRepository.findByProductNameContainingIgnoreCase(query, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findLowStockProducts(int threshold) {
        log.info("Fetching products with stock below: {}", threshold);
        return productRepository.findByQuantityInStockLessThan(threshold);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByPriceRange(Double minPrice, Double maxPrice) {
        log.info("Fetching products in price range: {} - {}", minPrice, maxPrice);
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findDiscountedProducts() {
        log.info("Fetching discounted products");
        return productRepository.findDiscountedProducts();
    }

    // ===== Helper Methods =====

    /**
     * Find or create a ProductLine based on category
     * This is useful for auto-assigning ProductLine when creating products
     */
    private Optional<ProductLine> findOrCreateProductLine(String category) {
        log.debug("Finding or creating ProductLine for category: {}", category);

        // Try to find existing ProductLine by name (assuming productLine field matches category)
        return productLineRepository.findByProductLine(category)
                .or(() -> {
                    // If not found, create a new ProductLine
                    log.info("Creating new ProductLine for category: {}", category);
                    ProductLine newProductLine = new ProductLine();
                    newProductLine.setProductLine(category);
                    newProductLine.setTextDescription("Auto-created for " + category);
                    newProductLine.setImage("/placeholder-" + category.toLowerCase() + ".jpg");

                    try {
                        ProductLine saved = productLineRepository.save(newProductLine);
                        return Optional.of(saved);
                    } catch (Exception e) {
                        log.error("Failed to create ProductLine for category: {}", category, e);
                        return Optional.empty();
                    }
                });
    }
}