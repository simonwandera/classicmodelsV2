package com.systech.systech.service;

import com.systech.systech.Entity.OrderDetails;

import java.util.List;

public interface OrderDetailsServiceI {
    List<OrderDetails> getOrderDetails();

    OrderDetails createOrUpdate(OrderDetails orderDetails);
}
