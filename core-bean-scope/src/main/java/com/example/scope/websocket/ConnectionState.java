package com.example.scope.websocket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ConnectionState {

    private int messagesSentInThisSession = 0;

    public void incrementMessageCount() {
        this.messagesSentInThisSession++;
    }

    public int getMessagesSent() {
        return messagesSentInThisSession;
    }
}
