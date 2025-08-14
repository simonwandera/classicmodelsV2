package com.systech.systech.Repository;

import com.systech.systech.Entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {

    // Find ProductLine by name
    Optional<ProductLine> findByProductLine(String productLine);

    // Find ProductLines that contain a keyword
    List<ProductLine> findByProductLineContainingIgnoreCase(String keyword);

    // Find ProductLines with images
    @Query("SELECT pl FROM ProductLine pl WHERE pl.image IS NOT NULL AND pl.image != ''")
    List<ProductLine> findProductLinesWithImages();

    // Find ProductLines without images
    @Query("SELECT pl FROM ProductLine pl WHERE pl.image IS NULL OR pl.image = ''")
    List<ProductLine> findProductLinesWithoutImages();

    // Check if ProductLine exists by name (case-insensitive)
    @Query("SELECT COUNT(pl) > 0 FROM ProductLine pl WHERE LOWER(pl.productLine) = LOWER(:productLine)")
    boolean existsByProductLineIgnoreCase(@Param("productLine") String productLine);


}
