package com.systech.systech.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Default constructor
@AllArgsConstructor // All-args constructor
public class OrderRequestDTO {
    private String orderNumber;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private String status;
    private String comments;
    private String customerNumber;
}
