package com.example.constructor.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processPayment(double amount) {
        // Simulating payment logic
        return amount > 0;
    }
}
