package com.systech.systech.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> findOrderItemsByOrderId(Long orderId) {
        String sql = """
            SELECT P.product_name, 
                   O.price_each, 
                   O.quantity_ordered, 
                   (O.price_each * O.quantity_ordered) AS total 
            FROM order_details O 
            INNER JOIN products P ON O.product_code_id = P.id 
            WHERE O.order_id = :orderId
            """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }
}