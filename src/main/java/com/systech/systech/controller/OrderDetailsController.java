package com.systech.systech.controller;

import com.systech.systech.Entity.OrderDetails;
import com.systech.systech.service.OrderDetailsServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class OrderDetailsController {
    private final OrderDetailsServiceI orderDetailsService;
    // GET all order details
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        try {
            List<OrderDetails> list = orderDetailsService.getOrderDetails();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            log.error("Error getting order details: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
//Get order details by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Long id) {
        try {
            OrderDetails orderDetails = orderDetailsService.getById(id);
            if (orderDetails != null) {
                return ResponseEntity.ok(orderDetails);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error getting order details by id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // CREATE new order details
    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails orderDetails) {
        log.info("Creating order details: {}", orderDetails);
        try {
            OrderDetails createdOrderDetails = orderDetailsService.createOrUpdate(orderDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetails);
        } catch (Exception e) {
            log.error("Error creating order details: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/createOrUpdateOrderDetails")
    public ResponseEntity<OrderDetails> createOrUpdateOrderDetails(@RequestBody OrderDetails orderDetails) {

        log.info("Creating or updating orderDetails line: {}", orderDetails);
        try {
            orderDetailsService.createOrUpdate(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetails);
    }

    // DELETE order details
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long id) {
        log.info("Deleting order details with id: {}", id);
        try {
            orderDetailsService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting order details with id {}: ", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
