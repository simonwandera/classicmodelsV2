
package com.systech.systech.Repository;

import com.systech.systech.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Define any custom query methods if needed
}

