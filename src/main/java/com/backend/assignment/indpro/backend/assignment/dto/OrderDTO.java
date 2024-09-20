package com.backend.assignment.indpro.backend.assignment.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@Data
@ToString
public class OrderDTO {
    private Long id;
    private Long userId;
    private List<OrderItemDTO> orderItems; // List of order items

   }
