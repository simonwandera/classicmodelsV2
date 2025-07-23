package com.systech.systech.Repository;

import com.systech.systech.Entity.ProductLine;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {

    List<ProductLine> findByProductLine(String productLine);
}
