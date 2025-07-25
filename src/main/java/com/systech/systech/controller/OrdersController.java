package com.systech.systech.controller;

import com.systech.systech.Entity.Orders;
import com.systech.systech.service.OrdersServiceI;
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
public class OrdersController {
    private final OrdersServiceI ordersService;

    // Get all orders - simplified without try-catch
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> list = ordersService.getOrders();
        return ResponseEntity.ok(list != null ? list : Collections.emptyList());
    }

    // Get order by ID - simplified without try-catch
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders order = ordersService.getById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    // Create new order - simplified without try-catch
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
        log.info("Creating order: {}", orders);
        Orders createdOrder = ordersService.createOrUpdate(orders);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // Update existing order - simplified without try-catch
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders orders) {
        log.info("Updating order with id {}: {}", id, orders);
        orders.setId(id);
        Orders updatedOrder = ordersService.createOrUpdate(orders);
        return ResponseEntity.ok(updatedOrder);
    }

    // Create or update orders - fixed logic
    @PostMapping("/createOrUpdateOrders")
    public ResponseEntity<Orders> createOrUpdateOrders(@RequestBody Orders orders) {
        log.info("Creating or updating orders: {}", orders);
        Orders result = ordersService.createOrUpdate(orders);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // Delete order - simplified without try-catch
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