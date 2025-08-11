package com.systech.systech.service;

import com.systech.systech.Dto.OrderItemDTO;
import com.systech.systech.Repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(Long orderId) {
        List<Object[]> results = orderItemRepository.findOrderItemsByOrderId(orderId);
        return convertToOrderItemDTOs(results);
    }

    private List<OrderItemDTO> convertToOrderItemDTOs(List<Object[]> results) {
        return results.stream()
                .map(this::mapRowToOrderItemDTO)
                .collect(Collectors.toList());
    }

    private OrderItemDTO mapRowToOrderItemDTO(Object[] row) {
        OrderItemDTO dto = new OrderItemDTO();

        // Product name
        dto.setProductName(row[0] != null ? row[0].toString() : "");

        // Price
        if (row[1] != null) {
            if (row[1] instanceof BigDecimal) {
                dto.setPriceEach((BigDecimal) row[1]);
            } else if (row[1] instanceof Double) {
                dto.setPriceEach(BigDecimal.valueOf((Double) row[1]));
            } else if (row[1] instanceof Float) {
                dto.setPriceEach(BigDecimal.valueOf(((Float) row[1]).doubleValue()));
            } else {
                dto.setPriceEach(new BigDecimal(row[1].toString()));
            }
        } else {
            dto.setPriceEach(BigDecimal.ZERO);
        }

        // Quantity
        if (row[2] != null) {
            if (row[2] instanceof Integer) {
                dto.setQuantityOrdered((Integer) row[2]);
            } else {
                dto.setQuantityOrdered(Integer.valueOf(row[2].toString()));
            }
        } else {
            dto.setQuantityOrdered(0);
        }

        // Total
        if (row[3] != null) {
            if (row[3] instanceof BigDecimal) {
                dto.setTotal((BigDecimal) row[3]);
            } else if (row[3] instanceof Double) {
                dto.setTotal(BigDecimal.valueOf((Double) row[3]));
            } else if (row[3] instanceof Float) {
                dto.setTotal(BigDecimal.valueOf(((Float) row[3]).doubleValue()));
            } else {
                dto.setTotal(new BigDecimal(row[3].toString()));
            }
        } else {
            dto.setTotal(BigDecimal.ZERO);
        }

        return dto;
    }
}
