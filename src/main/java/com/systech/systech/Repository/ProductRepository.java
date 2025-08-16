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

    // Basic finders
    Optional<Product> findByProductCode(String productCode);
    List<Product> findByProductLine(ProductLine productLine);

    // Category-based queries
    Page<Product> findByCategory(String category, Pageable pageable);

    // New products
    Page<Product> findByIsNewTrue(Pageable pageable);

    // Best-selling products
    Page<Product> findByIsBestSellerTrue(Pageable pageable);

    // Search products by name (case-insensitive)
    Page<Product> findByProductNameContainingIgnoreCase(String name, Pageable pageable);

    // Stock-related queries
    List<Product> findByQuantityInStockLessThan(Integer threshold);

    @Query("SELECT p FROM Product p WHERE p.quantityInStock = 0")
    List<Product> findOutOfStockProducts();

    // Price range queries
    @Query("SELECT p FROM Product p WHERE p.MSRP BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") Double minPrice,
                                   @Param("maxPrice") Double maxPrice);

    // Discounted products
    @Query("SELECT p FROM Product p WHERE p.originalPrice IS NOT NULL AND p.MSRP < p.originalPrice")
    List<Product> findDiscountedProducts();

    // Top sold products query (updated to handle null ProductLine)
    @Query("SELECT new com.systech.systech.Dto.ProductSalesDTO(" +
            "p.id, p.productName, p.productCode, p.MSRP, " +
            "COALESCE(pl.image, '/placeholder-product.jpg'), COUNT(od.id)) " +
            "FROM OrderDetail od " +
            "JOIN od.product p " +
            "LEFT JOIN p.productLine pl " +
            "GROUP BY p.id, p.productName, p.productCode, p.MSRP, pl.image " +
            "ORDER BY COUNT(od.id) DESC")
    List<ProductSalesDTO> findTopSoldProducts(Pageable pageable);

    // Most sold product (returns the actual Product entity, not DTO)
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN OrderDetail od ON od.product = p " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(od.id) DESC")
    List<Product> findProductsByPopularity(Pageable pageable);

    // Default method for getting most sold product
    default Optional<Product> findMostSoldProduct() {
        List<Product> products = findProductsByPopularity(PageRequest.of(0, 1));
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }

    // Advanced search queries
    @Query("SELECT p FROM Product p LEFT JOIN p.productLine pl " +
            "WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.productDescription) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.category) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(pl.productLine) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Product> findByAdvancedSearch(@Param("query") String query, Pageable pageable);

    // Category statistics
    @Query("SELECT p.category, COUNT(p) FROM Product p GROUP BY p.category")
    List<Object[]> countProductsByCategory();

    // Price statistics
    @Query("SELECT AVG(p.MSRP), MIN(p.MSRP), MAX(p.MSRP) FROM Product p")
    Object[] getPriceStatistics();

    // Products with images (have ProductLine with image)
    @Query("SELECT p FROM Product p JOIN p.productLine pl WHERE pl.image IS NOT NULL")
    List<Product> findProductsWithImages();

    // Products without images
    @Query("SELECT p FROM Product p WHERE p.productLine IS NULL OR p.productLine.image IS NULL")
    List<Product> findProductsWithoutImages();
}