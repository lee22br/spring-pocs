package com.example.scope.bean;

import com.example.scope.websocket.ConnectionState;
import com.example.scope.websocket.WebSocketScopeTestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpAttributes;
import org.springframework.messaging.simp.SimpAttributesContextHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(WebSocketScopeTestConfig.class) //import fake websocket thread
class WebSocketScopeTest {

    @Autowired
    private ConnectionState connectionState;

    @AfterEach
    void tearDown() {
        // Clean up the mocked WebSocket session
        SimpAttributesContextHolder.resetAttributes();
    }

    @Test
    void shouldMaintainStateWithinSameWebSocketSession() {
        // 1. Mock an active WebSocket Session
        Map<String, Object> sessionAttributes = new ConcurrentHashMap<>();
        SimpAttributes simpAttributes = new SimpAttributes("session-123", sessionAttributes);
        SimpAttributesContextHolder.setAttributes(simpAttributes);

        // 2. Interact with the WebSocket-scoped bean
        connectionState.incrementMessageCount();
        connectionState.incrementMessageCount();

        assertEquals(2, connectionState.getMessagesSent(), "Should have 2 messages in this session");
    }

    @Test
    void shouldHaveNewStateForDifferentWebSocketSession() {
        // 1. Mock a COMPLETELY NEW WebSocket Session
        Map<String, Object> sessionAttributes = new ConcurrentHashMap<>();
        SimpAttributes simpAttributes = new SimpAttributes("session-999", sessionAttributes);
        SimpAttributesContextHolder.setAttributes(simpAttributes);

        assertEquals(0, connectionState.getMessagesSent(), "A new WebSocket connection should start with 0 messages");
    }
}
