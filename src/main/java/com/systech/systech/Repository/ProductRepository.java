package com.systech.systech.Repository;

import com.systech.systech.Dto.ProductSalesDTO;
import com.systech.systech.Entity.Product;
import com.systech.systech.Entity.ProductLine;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByProductCode(String productCode);

    List<Product> findByProductLine(ProductLine productLine);


    @Query("SELECT p as product, COUNT(oi.id) as totalSold " +
            "FROM OrderDetail oi JOIN oi.product p " +
            "GROUP BY p " +
            "ORDER BY totalSold DESC")
    List<ProductSalesDTO> findTopSoldProducts(Pageable pageable);

    default Optional<Product> findMostSoldProduct() {
        return findTopSoldProducts(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(ProductSalesDTO::getProduct);
    }
}
