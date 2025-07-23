package com.systech.systech.controller;

import com.systech.systech.Entity.Product;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

}