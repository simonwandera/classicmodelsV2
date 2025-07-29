package com.systech.systech.controller;

import com.systech.systech.Entity.ProductLine;
import com.systech.systech.service.ProductLineService;
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

    private final ProductLineService productLineService;

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
        if (product.getProductLine() == null || product.getProductLine().trim().isEmpty()) {
            log.error("ProductLine field is required");
            return ResponseEntity.badRequest().build();
        }

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
