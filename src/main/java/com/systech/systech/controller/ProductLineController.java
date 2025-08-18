package com.systech.systech.controller;

import com.systech.systech.Entity.ProductLine;
import com.systech.systech.service.FileStorageService;
import com.systech.systech.service.ProductLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/product-lines")
@RequiredArgsConstructor
@Slf4j

public class ProductLineController {


    private final ProductLineService productLineService;
    private final FileStorageService fileStorageService;
    // GET all
    @GetMapping
    public ResponseEntity<List<ProductLine>> getAll() {
        return ResponseEntity.ok(productLineService.getAllProductLines());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductLine> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productLineService.getProductLineById(id));
    }

    //CREATE
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductLine> create(
            @RequestPart("data") ProductLine productLine,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        if (imageFile != null) {
            productLine.setImage(fileStorageService.storeFile(imageFile));
        }

        return ResponseEntity.ok(productLineService.createProductLine(productLine));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductLine> update(@PathVariable Long id,
                                              @RequestBody ProductLine productLine) {
        return ResponseEntity.ok(productLineService.updateProductLine(id, productLine));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productLineService.deleteProductLine(id);
        return ResponseEntity.noContent().build();
    }

}
