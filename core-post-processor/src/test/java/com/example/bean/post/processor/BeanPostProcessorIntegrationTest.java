package com.example.bean.post.processor;

import com.example.bean.post.processor.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BeanPostProcessorIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void testBeanPostProcessorInjectedSystemId() {
        // The bean was already intercepted and modified during application startup!
        String currentSystemId = paymentService.getSystemId();
        String resultMessage = paymentService.processPayment();

        assertNotNull(currentSystemId, "System ID should not be null");

        // BeanPostProcessor injected the exact string
        assertEquals("PAYPAL-TRACK-001", currentSystemId);

        // bean with the injected property
        assertEquals("Payment processed by system ID: PAYPAL-TRACK-001", resultMessage);
    }
}
