package com.systech.systech.controller;

import com.systech.systech.Entity.OrderDetails;
import com.systech.systech.service.OrderDetailsServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/orderDetails")
@RequiredArgsConstructor
@Slf4j
public class OrderDetailsController {
    private final OrderDetailsServiceI orderDetailsService;

    // Get all order details - simplified
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> list = orderDetailsService.getOrderDetails();
        return ResponseEntity.ok(list != null ? list : Collections.emptyList());
    }

    // Get order details by ID - simplified
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Long id) {
        OrderDetails orderDetails = orderDetailsService.getById(id);
        if (orderDetails != null) {
            return ResponseEntity.ok(orderDetails);
        }
        return ResponseEntity.notFound().build();
    }

    // Create new order details - simplified
    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails orderDetails) {
        log.info("Creating order details: {}", orderDetails);
        OrderDetails createdOrderDetails = orderDetailsService.createOrUpdate(orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetails);
    }

    // Create or update order details - fixed logic
    @PostMapping("/createOrUpdateOrderDetails")
    public ResponseEntity<OrderDetails> createOrUpdateOrderDetails(@RequestBody OrderDetails orderDetails) {
        log.info("Creating or updating orderDetails line: {}", orderDetails);
        OrderDetails result = orderDetailsService.createOrUpdate(orderDetails);
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