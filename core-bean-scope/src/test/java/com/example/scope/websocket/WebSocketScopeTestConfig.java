package com.example.scope.websocket;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.SimpleThreadScope;

@TestConfiguration
public class WebSocketScopeTestConfig {

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        // Faked websocket in thread
        configurer.addScope("websocket", new SimpleThreadScope());
        return configurer;
    }
}
