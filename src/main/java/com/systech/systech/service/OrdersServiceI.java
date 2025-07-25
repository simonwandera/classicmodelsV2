package com.systech.systech.service;

import com.systech.systech.Entity.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdersServiceI {
    List<Orders> getOrders();
    Orders getById(Long id);
    Orders createOrUpdate(Orders orders);
    void delete(Long id);
}
