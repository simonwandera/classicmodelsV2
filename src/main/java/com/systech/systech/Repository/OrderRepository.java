
package com.systech.systech.Repository;

import com.systech.systech.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT COUNT(o.id) FROM Order o WHERE o.createdAt between :startYear AND :endYear")
    Optional<Long> findOrderVolumeByYear(LocalDateTime startYear, LocalDateTime endYear);


}

