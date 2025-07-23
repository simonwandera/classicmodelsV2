
package com.systech.systech.Repository;

import com.systech.systech.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // Define any custom query methods if needed
}

