package com.systech.systech.service;

import com.systech.systech.Entity.Orders;
import com.systech.systech.Repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService implements OrdersServiceI{
    private final OrdersRepository ordersRepository;

    public List<Orders> getOrders() {

        return ordersRepository.findAll();
    }
    @Override
    public Orders getById(Long id) {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        }
        log.warn("Order with id {} not found", id);
        return null;
    }

    @Override
    public Orders createOrUpdate(Orders orders) {

        return  ordersRepository.save(orders);
    }

    @Override
    public void delete(Long id) {
        if (ordersRepository.existsById(id)) {
            ordersRepository.deleteById(id);
            log.info("Order with id {} deleted successfully", id);
        } else {
            log.warn("Cannot delete Order with id {} - not found", id);
            throw new RuntimeException("Order not found with id: " + id);
        }
    }
}
