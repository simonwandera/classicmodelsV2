package com.systech.systech.Repository;

import com.systech.systech.Dto.ProductSalesDTO;
import com.systech.systech.Entity.Product;
import com.systech.systech.Entity.ProductLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByProductCode(String productCode);

    List<Product> findByProductLine(ProductLine productLine);
    // Find products by category

    Page<Product> findByCategory(String category, Pageable pageable);



    // Find new products
    Page<Product> findByIsNewTrue(Pageable pageable);

    // Find best-selling products
    Page<Product> findByIsBestSellerTrue(Pageable pageable);

    // Search products by name (case-insensitive)


    // Paginated product search
    Page<Product> findByProductNameContainingIgnoreCase(String name, Pageable pageable);

    // Find products with stock below certain threshold


    // Top sold products query (updated to match new entity structure)
    @Query("SELECT new com.systech.systech.Dto.ProductSalesDTO(" +
            "p.id, p.productName, p.productCode, p.MSRP, " +
            "pl.image, COUNT(od.id)) " +
            "FROM OrderDetail od " +
            "JOIN od.product p " +
            "LEFT JOIN p.productLine pl " +
            "GROUP BY p.id, p.productName, p.productCode, p.MSRP, pl.image " +
            "ORDER BY COUNT(od.id) DESC")
    List<ProductSalesDTO> findTopSoldProducts(Pageable pageable);
    // Most sold product (default method remains the same)
    default Optional<Product> findMostSoldProduct() {
        return findTopSoldProducts(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(ProductSalesDTO::getProduct);
    }

    // Find products in price range
    @Query("SELECT p FROM Product p WHERE p.MSRP BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice,
                                   @Param("maxPrice") Double maxPrice);

    // Find products with discount (price < originalPrice)
    @Query("SELECT p FROM Product p WHERE p.originalPrice IS NOT NULL AND p.MSRP < p.originalPrice")
    List<Product> findDiscountedProducts();
}
