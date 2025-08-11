package com.systech.systech.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("priceEach")
    private BigDecimal priceEach;

    @JsonProperty("quantityOrdered")
    private Integer quantityOrdered;

    @JsonProperty("total")
    private BigDecimal total;
}
