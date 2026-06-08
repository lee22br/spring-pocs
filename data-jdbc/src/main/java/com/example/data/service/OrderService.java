package com.example.data.service;

import com.example.data.domain.CustomerOrder;
import com.example.data.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public CustomerOrder shipOrder(Long orderId) {
        CustomerOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));

        CustomerOrder updatedOrder = order.withStatus("SHIPPED");

        return orderRepository.save(updatedOrder);
    }
}