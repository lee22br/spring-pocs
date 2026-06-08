package com.example.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("order_item")
public record OrderItem(
        @Id Long id,
        String productName,
        int quantity,
        BigDecimal price
) {}