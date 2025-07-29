package com.systech.systech.Repository;

import com.systech.systech.Entity.Product;
import com.systech.systech.Entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByProductCode(String productCode);

    List<Product> findByProductLine(ProductLine productLine);

}
