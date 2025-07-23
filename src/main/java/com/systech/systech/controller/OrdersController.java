package com.systech.systech.controller;

import com.systech.systech.Entity.Orders;
import com.systech.systech.service.OrdersServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {
    private final OrdersServiceI ordersService;

    // POST, GET, PUT, DELETE, PATCH, OPTIONS, HEAD, TRACE
    // Define endpoints for orders-related operations here
    // For example, you can create methods to handle CRUD operations for orders

    //     Example method to get all orders
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        try {
            List<Orders> list = ordersService.getOrders();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("Error getting orders: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        try {
            Orders order = ordersService.getById(id);
            if (order != null) {
                return ResponseEntity.ok(order);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error getting order by id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //CREATE new order
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
        log.info("Creating order: {}", orders);
        try {
            Orders createdOrder = ordersService.createOrUpdate(orders);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Exception e) {
            log.error("Error creating order: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // UPDATE existing order
    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders orders) {
        log.info("Updating order with id {}: {}", id, orders);
        try {
            orders.setId(id);
            Orders updatedOrder = ordersService.createOrUpdate(orders);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            log.error("Error updating order with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/createOrUpdateOrders")
    public ResponseEntity<Orders> createOrUpdateOrders(@RequestBody Orders orders) {

        log.info("Creating or updating orders: {}", orders);
        try {
            ordersService.createOrUpdate(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }

    // DELETE order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.info("Deleting order with id: {}", id);
        try {
            ordersService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting order with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
