package com.systech.systech.service;

import com.systech.systech.Entity.Product;
import com.systech.systech.Repository.OrderRepository;
import com.systech.systech.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;


    @Override
    public String getMostSoldProduct() {

         Product product =  productRepository.findMostSoldProduct()
                 .orElse(null);

         return product != null ? product.getProductName() : "None";
    }

    @Override
    public List<Product> getAll(){
        log.info("Fetching all products");
        return productRepository.findAll();

    }

    @Override
    public Optional<Product> getById(Long id) {
        log.info("Fetching product by ID: {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getByProductCode(String productCode){
        log.info("Fetching product by Code:{}", productCode);
        return productRepository.findByProductCode(productCode);
    }

    @Override
    public Product create(Product product) {
        log.info("Creating product: {}", product.getProductName());
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product updatedProduct) {
        log.info("Updating product with ID: {}", id);
        return productRepository.findById(id).map(existing -> {
            updatedProduct.setId(id);
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
}
