package com.example.constructor.manager;

import com.example.constructor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConstructorCartManager {

    // final cannot be changed after initialization
    private final PaymentService paymentService;

    // Spring uses this constructor to inject PaymentService
   public ConstructorCartManager(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String checkout(double amount) {
        boolean success = paymentService.processPayment(amount);
        return success ? "Checkout successful (Constructor)" : "Checkout failed";
    }
}
