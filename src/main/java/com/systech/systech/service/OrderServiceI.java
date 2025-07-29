package com.systech.systech.service;

import com.systech.systech.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServiceI {
    List<Order> getOrders();
    Order getById(Long id);
    Order createOrUpdate(Order order);
    void delete(Long id);
}
