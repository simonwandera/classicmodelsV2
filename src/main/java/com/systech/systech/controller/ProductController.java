package com.systech.systech.controller;

import com.systech.systech.Entity.Product;
import com.systech.systech.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Get all products with pagination support
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productName") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "false") boolean paginated) {

        if (paginated) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
            Page<Product> result = productService.getAll(pageable);
            return ResponseEntity.ok(result);
        } else {
            List<Product> result = productService.getAll();
            return ResponseEntity.ok(result);
        }
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get product by product code
    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getByProductCode(@PathVariable String code) {
        return productService.getByProductCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a product
    @PostMapping()
    public ResponseEntity<Product> create(@Validated @RequestBody Product product) {
        log.info("POST /api/product - Creating product: {}", product.getProductName());

        // Log the received product data for debugging
        log.debug("Received product data: {}", product);

        Product createdProduct = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Update a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Validated @RequestBody Product product) {

        log.info("PUT /api/product/{} - Updating product", id);
        return productService.update(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/product/{}", id);
        return productService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Get products by category with pagination
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<Product>> getByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productName") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        log.info("GET /api/product/category/{}", category);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        Page<Product> result = productService.findByCategory(category, pageable);
        return ResponseEntity.ok(result);
    }

    // Get new arrivals (recently created products)
    @GetMapping("/new-arrivals")
    public ResponseEntity<Page<Product>> getNewArrivals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("GET /api/product/new-arrivals");
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(productService.findNewArrivals(pageable));
    }

    // Get best-selling products
    @GetMapping("/best-sellers")
    public ResponseEntity<Page<Product>> getBestSellers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("GET /api/product/best-sellers");
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.findBestSellers(pageable));
    }

    // Search for products
    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProducts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("GET /api/product/search?query={}", query);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> result = productService.searchProducts(query, pageable);
        return ResponseEntity.ok(result);
    }

    // Get the most sold product
    @GetMapping("/most-sold")
    public ResponseEntity<String> getMostSoldProduct() {
        log.info("GET /api/product/most-sold");
        return ResponseEntity.ok(productService.getMostSoldProduct());
    }

    // Get products with low stock
    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts(
            @RequestParam(defaultValue = "5") int threshold) {
        log.info("GET /api/product/low-stock with threshold: {}", threshold);
        return ResponseEntity.ok(productService.findLowStockProducts(threshold));
    }

    // Get products in price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        log.info("GET /api/product/price-range from {} to {}", minPrice, maxPrice);
        return ResponseEntity.ok(productService.findByPriceRange(minPrice, maxPrice));
    }

    // Get discounted products
    @GetMapping("/discounted")
    public ResponseEntity<List<Product>> getDiscountedProducts() {
        log.info("GET /api/product/discounted");
        return ResponseEntity.ok(productService.findDiscountedProducts());
    }
}