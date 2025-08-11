package com.systech.systech.service;

import com.systech.systech.Dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> getOrderItemsByOrderId(Long orderId);
}
