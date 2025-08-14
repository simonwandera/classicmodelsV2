package com.systech.systech.controller;

import com.systech.systech.Dto.CompleteOrderDTO;
import com.systech.systech.Dto.CustomerDTO;
import com.systech.systech.Dto.OrderItemDTO;
import com.systech.systech.Dto.OrderRequestDTO;
import com.systech.systech.Entity.Customer;
import com.systech.systech.Entity.Order;
import com.systech.systech.Repository.CustomerRepository;
import com.systech.systech.service.OrderItemService;
import com.systech.systech.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService ordersService;
    private final OrderItemService orderItemService;

    // ---------------- Get All Orders ---------------- //
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> list = ordersService.getOrders();
        return ResponseEntity.ok(list != null ? list : Collections.emptyList());
    }

    // ---------------- Get Order By ID ---------------- //
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = ordersService.getById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    // ---------------- Create Order ---------------- //
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        log.info("Creating order: {}", orderRequestDTO);
        Order order = mapToEntity(orderRequestDTO);
        Order createdOrder = ordersService.createOrUpdate(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    // ---------------- Update Order ---------------- //
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO orderRequestDTO) {
        log.info("Updating order with id {}: {}", id, orderRequestDTO);
        Order order = mapToEntity(orderRequestDTO);
        order.setId(id);
        Order updatedOrder = ordersService.createOrUpdate(order);
        return ResponseEntity.ok(updatedOrder);
    }

    // ---------------- Create or Update Orders (Bulk or Single) ---------------- //
    @PostMapping("/createOrUpdateOrders")
    public ResponseEntity<Order> createOrUpdateOrders(@RequestBody OrderRequestDTO orderRequestDTO) {
        log.info("Creating or updating orders: {}", orderRequestDTO);
        Order order = mapToEntity(orderRequestDTO);
        Order result = ordersService.createOrUpdate(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // ---------------- Delete Order ---------------- //
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.info("Deleting order with id: {}", id);
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- Get Order Items ---------------- //
    @GetMapping("/{orderId}/items")
    public ResponseEntity<List<OrderItemDTO>> getOrderItems(@PathVariable Long orderId) {
        try {
            log.info("Fetching items for order {}", orderId);
            List<OrderItemDTO> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(orderItems != null ? orderItems : Collections.emptyList());
        } catch (Exception e) {
            log.error("Error fetching order items for order {}: {}", orderId, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ---------------- Get Complete Order Details ---------------- //
    @GetMapping("/{orderId}/complete")
    public ResponseEntity<CompleteOrderDTO> getCompleteOrder(@PathVariable Long orderId) {
        log.info("Fetching complete order details for order {}", orderId);

        Order order = ordersService.getById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        List<OrderItemDTO> orderItems = orderItemService.getOrderItemsByOrderId(orderId);

        CustomerDTO customerDTO = new CustomerDTO(
                order.getCustomer().getId(),
                order.getCustomer().getCustomerName(),
                order.getCustomer().getCustomerNumber()
        );
        customerDTO.setPhone(order.getCustomer().getPhone());
        customerDTO.setAddress(order.getCustomer().getAddressLine1());

        CompleteOrderDTO completeOrder = new CompleteOrderDTO();
        completeOrder.setId(order.getId());
        completeOrder.setOrderNumber(order.getOrderNumber());
        completeOrder.setOrderDate(order.getOrderDate());
        completeOrder.setRequiredDate(order.getRequiredDate());
        completeOrder.setShippedDate(order.getShippedDate());
        completeOrder.setStatus(order.getStatus());
        completeOrder.setComments(order.getComments());
        completeOrder.setCustomer(customerDTO);
        completeOrder.setItems(orderItems);
        completeOrder.setTotalAmount(calculateTotalAmount(orderItems));

        return ResponseEntity.ok(completeOrder);
    }

    // ---------------- Exception Handlers ---------------- //
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        log.error("Error occurred: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "An error occurred while processing your request"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
        log.error("Invalid argument: ", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", e.getMessage()));
    }

    // ---------------- Private Helpers ---------------- //
    private BigDecimal calculateTotalAmount(List<OrderItemDTO> orderItems) {
        if (orderItems == null || orderItems.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return orderItems.stream()
                .map(OrderItemDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private final CustomerRepository customerRepository;
    private Order mapToEntity(OrderRequestDTO dto) {
        Order order = new Order();
        order.setOrderNumber(dto.getOrderNumber());
        order.setOrderDate(dto.getOrderDate());
        order.setRequiredDate(dto.getRequiredDate());
        order.setShippedDate(dto.getShippedDate());
        order.setStatus(dto.getStatus());
        order.setComments(dto.getComments());

        Customer customer = customerRepository.findByCustomerNumber(dto.getCustomerNumber())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + dto.getCustomerNumber()));
        order.setCustomer(customer);
        return order;
    }
}
