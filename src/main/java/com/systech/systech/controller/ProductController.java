package com.systech.systech.controller;

import com.systech.systech.Entity.Product;
import com.systech.systech.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {

    // POST, GET, PUT, DELETE, PATCH, OPTIONS, HEAD, TRACE
    // Define endpoints for product-related operations here
    // For example, you can create methods to handle CRUD operations for products

//     Example method to get all products
     @GetMapping("/products")
     public ResponseEntity<List<Product>> getAllProducts() {
         Product product = new Product();

         product.setProductName("Sample Product");

         if (product.getId() == null ) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
         }

//         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
         return ResponseEntity.ok(List.of(product));
     }
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        log.info("GET /api/products");
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        log.info("GET /api/products/{}", id);
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getByProductCode(@PathVariable String code) {
        log.info("GET /api/products/code/{}", code);
        return productService.getByProductCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@Validated @RequestBody Product product) {
        log.info("POST /api/products");
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Validated @RequestBody Product product) {
        log.info("PUT /api/products/{}", id);
        return productService.update(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/products/{}", id);
        return productService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
