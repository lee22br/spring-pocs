package com.example.constructor;

import com.example.constructor.manager.ConstructorCartManager;
import com.example.constructor.manager.SetterCartManager;
import com.example.constructor.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DependencyInjectionTest {

    @Autowired
    private SetterCartManager setterCartManager;

    @Test
    void testSetterInjectionWithSpring() {
        //Spring automatically inject PaymentService
        String result = setterCartManager.checkout(100.0);

        assertEquals("Checkout successful (Setter)", result);
    }

    @Test
    void testConstructorInjectionWithoutSpring() {

        PaymentService paypalService = new PaymentService();

        // Passing the dependency via constructor
        ConstructorCartManager manager = new ConstructorCartManager(paypalService);

        String result = manager.checkout(50.0);
        assertEquals("Checkout successful (Constructor)", result);
    }
}