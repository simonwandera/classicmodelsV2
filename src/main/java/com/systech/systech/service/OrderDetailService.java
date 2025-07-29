package com.systech.systech.service;

import com.systech.systech.Entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetails();
    OrderDetail getById(Long id);
    OrderDetail createOrUpdate(OrderDetail orderDetail);
    void delete(Long id);
}
