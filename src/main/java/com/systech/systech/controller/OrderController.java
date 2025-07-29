package com.systech.systech.controller;

import com.systech.systech.Entity.Order;
import com.systech.systech.service.OrderServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderServiceI ordersService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> list = ordersService.getOrders();
        return ResponseEntity.ok(list != null ? list : Collections.emptyList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = ordersService.getById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        log.info("Creating order: {}", order);
        Order createdOrder = ordersService.createOrUpdate(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // Update existing order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        log.info("Updating order with id {}: {}", id, order);
        order.setId(id);
        Order updatedOrder = ordersService.createOrUpdate(order);
        return ResponseEntity.ok(updatedOrder);
    }

    // Create or update orders
    @PostMapping("/createOrUpdateOrders")
    public ResponseEntity<Order> createOrUpdateOrders(@RequestBody Order order) {
        log.info("Creating or updating orders: {}", order);
        Order result = ordersService.createOrUpdate(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.info("Deleting order with id: {}", id);
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Global exception handler for this controller
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        log.error("Error occurred: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "An error occurred while processing your request"));
    }

    // You can add more specific exception handlers
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
        log.error("Invalid argument: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
    }
}