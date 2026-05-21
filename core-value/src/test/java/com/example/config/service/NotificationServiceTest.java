package com.example.config.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// mock application.properties values directly in the test!
@SpringBootTest( properties = {
        "notification.sender.email=test-sender@test.com",
        "notification.max-retries=10"
})
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    void shouldInjectPropertiesAndUseDefaultValues() {

        String summary = notificationService.getConfigurationSummary();

        // @SpringBootTest properties were injected
        // and that the fallback default value (5000) worked for the missing timeout.
        assertEquals("Sending from: test-sender@test.com | Retries: 10 | Timeout: 5000 ms", summary);
    }
}
