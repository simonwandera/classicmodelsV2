package com.systech.systech.controller;

import com.systech.systech.Entity.OrderDetails;
import com.systech.systech.service.OrderDetailsServiceI;
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
@RequestMapping("api/orderDetails")
@RequiredArgsConstructor
@Slf4j
public class OrderDetailsController {
    private final OrderDetailsServiceI orderDetailsService;
    @GetMapping
    public ResponseEntity<List<OrderDetails>> getList() {
        List <OrderDetails> list = orderDetailsService.getOrderDetails();

//         ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        return ResponseEntity.ok(list);
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
}
