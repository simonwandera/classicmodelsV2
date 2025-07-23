package com.systech.systech.service;

import com.systech.systech.Entity.OrderDetails;
import com.systech.systech.Repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService implements OrderDetailsServiceI {

    private final OrderDetailsRepository orderDetailsRepository;

    public List <OrderDetails> getOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails createOrUpdate(OrderDetails orderDetails) {
        return  orderDetailsRepository.save(orderDetails);
    }
}
