package com.systech.systech.controller;

import com.systech.systech.Entity.Orders;
import com.systech.systech.service.OrdersServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<List<Orders>> getList() {
        List<Orders> list = ordersService.getOrders();

//         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        return ResponseEntity.ok(list);
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
}
