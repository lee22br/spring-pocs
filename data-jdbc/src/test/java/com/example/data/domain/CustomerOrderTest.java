package com.example.data.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderTest {

    @Test
    @DisplayName("Should create a new copy of CustomerOrder with the updated status")
    void shouldUpdateStatusCorrectly() {
        // Arrange
        OrderItem item = new OrderItem(1L, "Keyboard", 1, new BigDecimal("120.00"));
        CustomerOrder originalOrder = new CustomerOrder(100L, "Alice", "PENDING", Set.of(item));

        // Act
        CustomerOrder updatedOrder = originalOrder.withStatus("SHIPPED");

        // Assert
        assertNotNull(updatedOrder);
        assertEquals("SHIPPED", updatedOrder.status());

        assertEquals(originalOrder.id(), updatedOrder.id());
        assertEquals(originalOrder.customerName(), updatedOrder.customerName());
        assertEquals(originalOrder.items(), updatedOrder.items());

        assertNotSame(originalOrder, updatedOrder);
    }
}
