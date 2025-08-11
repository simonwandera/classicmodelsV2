package com.systech.systech.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class CompleteOrderDTO {
    private Long id;
    private String orderNumber;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private String status;
    private String comments;
    private CustomerDTO customer;
    private List<OrderItemDTO> items;
    private BigDecimal totalAmount;

}


