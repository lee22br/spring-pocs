package com.example.constructor.manager;

import com.example.constructor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetterCartManager {

    private PaymentService paymentService;

    public SetterCartManager() {

    }

    // Spring use this method to inject the dependency
    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String checkout(double amount) {
        if (null == this.paymentService) {
            throw new IllegalStateException("PaymentService is missing!");
        }
        boolean success = paymentService.processPayment(amount);
        return success ? "Checkout successful (Setter)" : "Checkout failed";
    }
}
