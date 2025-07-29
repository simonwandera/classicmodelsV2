package com.systech.systech.service;

import com.systech.systech.Entity.OrderDetail;
import com.systech.systech.Repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    @Override
    public List <OrderDetail> getOrderDetails() {

        return orderDetailRepository.findAll();
    }
    @Override
    public OrderDetail getById(Long id) {
        Optional<OrderDetail> orderDetails = orderDetailRepository.findById(id);
        if (orderDetails.isPresent()) {
            return orderDetails.get();
        }
        log.warn("OrderDetails with id {} not found", id);
        return null;
    }
    @Override
    public OrderDetail createOrUpdate(OrderDetail orderDetail) {

        return  orderDetailRepository.save(orderDetail);
    }
    @Override
    public void delete(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
            log.info("OrderDetails with id {} deleted successfully", id);
        } else {
            log.warn("Cannot delete OrderDetails with id {} - not found", id);
            throw new RuntimeException("OrderDetails not found with id: " + id);
        }
    }
}
