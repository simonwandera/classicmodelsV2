package com.systech.systech.controller;

import com.systech.systech.Entity.OrderDetail;
import com.systech.systech.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/orderDetails")
@RequiredArgsConstructor
@Slf4j
public class OrderDetailController {
    private final OrderDetailService orderDetailsService;

    // Get all order details - simplified
    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> list = orderDetailsService.getOrderDetails();
        return ResponseEntity.ok(list != null ? list : Collections.emptyList());
    }

    // Get order details by ID - simplified
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailsById(@PathVariable Long id) {
        OrderDetail orderDetail = orderDetailsService.getById(id);
        if (orderDetail != null) {
            return ResponseEntity.ok(orderDetail);
        }
        return ResponseEntity.notFound().build();
    }

    // Create new order details - simplified
    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetails(@RequestBody OrderDetail orderDetail) {
        log.info("Creating order details: {}", orderDetail);
        OrderDetail createdOrderDetail = orderDetailsService.createOrUpdate(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
    }

    // Create or update order details - fixed logic
    @PostMapping("/createOrUpdateOrderDetails")
    public ResponseEntity<OrderDetail> createOrUpdateOrderDetails(@RequestBody OrderDetail orderDetail) {
        log.info("Creating or updating orderDetails line: {}", orderDetail);
        OrderDetail result = orderDetailsService.createOrUpdate(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // Delete order details - simplified
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long id) {
        log.info("Deleting order details with id: {}", id);
        orderDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}