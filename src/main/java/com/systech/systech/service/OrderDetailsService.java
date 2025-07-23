package com.systech.systech.service;

import com.systech.systech.Entity.OrderDetails;
import com.systech.systech.Repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailsService implements OrderDetailsServiceI {

    private final OrderDetailsRepository orderDetailsRepository;
    @Override
    public List <OrderDetails> getOrderDetails() {

        return orderDetailsRepository.findAll();
    }
    @Override
    public OrderDetails getById(Long id) {
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        if (orderDetails.isPresent()) {
            return orderDetails.get();
        }
        log.warn("OrderDetails with id {} not found", id);
        return null;
    }
    @Override
    public OrderDetails createOrUpdate(OrderDetails orderDetails) {

        return  orderDetailsRepository.save(orderDetails);
    }
    @Override
    public void delete(Long id) {
        if (orderDetailsRepository.existsById(id)) {
            orderDetailsRepository.deleteById(id);
            log.info("OrderDetails with id {} deleted successfully", id);
        } else {
            log.warn("Cannot delete OrderDetails with id {} - not found", id);
            throw new RuntimeException("OrderDetails not found with id: " + id);
        }
    }
}
