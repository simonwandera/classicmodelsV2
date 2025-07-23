package com.systech.systech.controller;

import com.systech.systech.Entity.ProductLine;
import com.systech.systech.service.ProductLineServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/productLine")
@RequiredArgsConstructor
@Slf4j
public class ProductLineController {

    private final ProductLineServiceI productLineService;

    // POST, GET, PUT, DELETE, PATCH, OPTIONS, HEAD, TRACE
    // Define endpoints for product-related operations here
    // For example, you can create methods to handle CRUD operations for products

    //     Example method to get all products
    @GetMapping
    public ResponseEntity<List<ProductLine>> getList() {
        List<ProductLine> list = productLineService.getProductList();

//         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/createOrUpdateProduct")
    public ResponseEntity<ProductLine> createOrUpdateProductLine(@RequestBody ProductLine product) {

        log.info("Creating or updating product line: {}", product);
        try {
            productLineService.createOrUpdate(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }


}