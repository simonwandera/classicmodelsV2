package com.systech.systech.service;

import com.systech.systech.Entity.Order;
import com.systech.systech.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderServiceI {
    private final OrderRepository orderRepository;

    public List<Order> getOrders() {

        return orderRepository.findAll();
    }
    @Override
    public Order getById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        return null;
    }

    @Override
    public Order createOrUpdate(Order order) {

        return  orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            log.info("Order with id {} deleted successfully", id);
        } else {
            log.warn("Cannot delete Order with id {} - not found", id);
            throw new RuntimeException("Order not found with id: " + id);
        }
    }
}
