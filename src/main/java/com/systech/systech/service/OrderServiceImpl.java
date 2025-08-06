package com.systech.systech.service;

import com.systech.systech.Entity.Order;
import com.systech.systech.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final AuditLogService auditLogService;

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

        order = orderRepository.save(order);
        auditLogService.saveOrUpdate("Created Order", "Order - " + order.getOrderNumber());
        return order;
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


    @Override
    public BigDecimal getOrderVolumeThisYear() {

        int year = LocalDate.now().getYear();

        LocalDateTime firstDay = Year.of(year).atDay(1).atStartOfDay();
        LocalDateTime lastDay = Year.of(year).atDay(Year.of(year).length()).atTime(LocalTime.MAX);

        Long orders = orderRepository.findOrderVolumeByYear(firstDay, lastDay)
                .orElse(0L);

        return new BigDecimal(orders);
    }
}
