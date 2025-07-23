package com.systech.systech.service;

import com.systech.systech.Entity.Orders;
import com.systech.systech.Repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrdersService implements OrdersServiceI{
    private final OrdersRepository ordersRepository;

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders createOrUpdate(Orders orders) {
        return  ordersRepository.save(orders);
    }
}
