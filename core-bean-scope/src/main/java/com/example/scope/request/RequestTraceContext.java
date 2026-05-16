package com.example.scope.request;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Component
@RequestScope
public class RequestTraceContext {

    private final String traceId;

    public RequestTraceContext() {
        // Generates a random unique ID for each instance
        this.traceId = UUID.randomUUID().toString();
        System.out.println("New RequestTraceContext created with ID: " + this.traceId);
    }

    public String getTraceId() {
        return traceId;
    }
}