package com.example.config.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final String senderEmail;
    private final int maxRetries;
    private final int timeoutMs;

    // Injecting values through the constructor
    public NotificationService(
            @Value("${notification.sender.email}") String senderEmail,
            @Value("${notification.max-retries}") int maxRetries,
            @Value("${notification.timeout:5000}") int timeoutMs) { // 5000 is the default

        this.senderEmail = senderEmail;
        this.maxRetries = maxRetries;
        this.timeoutMs = timeoutMs;
    }

    public String getConfigurationSummary() {
        return String.format("Sending from: %s | Retries: %d | Timeout: %d ms",
                senderEmail, maxRetries, timeoutMs);
    }
}