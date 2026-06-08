package com.example.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("customer_order")
public record CustomerOrder<OrderItem>(
        @Id Long id,
        String customerName,

        String status,

        @MappedCollection(idColumn = "customer_order_id")
        Set<OrderItem> items
) {
    public CustomerOrder withStatus(String newStatus) {
        return new CustomerOrder(this.id, this.customerName, newStatus, this.items);
    }
}