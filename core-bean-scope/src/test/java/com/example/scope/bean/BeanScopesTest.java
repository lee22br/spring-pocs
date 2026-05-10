package com.example.scope.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeanScopesTest {

    // Injecting the ApplicationContext
    @Autowired
    private ApplicationContext context;

    @Test
    void testSingletonScope() {
        SingletonTicketGenerator instance1 = context.getBean(SingletonTicketGenerator.class);
        SingletonTicketGenerator instance2 = context.getBean(SingletonTicketGenerator.class);

        // They are the EXACT SAME object in memory
        assertSame(instance1, instance2, "Instances should be the exact same object");

        instance1.generateTicket(); // instance1 increments to 1
        instance2.generateTicket(); // instance2 increments to 2

        // Must return 2
        assertEquals(2, instance1.getCurrentTicketNumber());
    }

    @Test
    void testPrototypeScope() {
        PrototypeTicketGenerator instance1 = context.getBean(PrototypeTicketGenerator.class);
        PrototypeTicketGenerator instance2 = context.getBean(PrototypeTicketGenerator.class);

        // They are DIFFERENT objects in memory
        assertNotSame(instance1, instance2, "Instances should be different objects");

        instance1.generateTicket(); // instance1 increments to 1

        // instance2 has its own state and starts at 0
        assertEquals(0, instance2.getCurrentTicketNumber());

        instance2.generateTicket(); // instance2 increments to 1
        assertEquals(1, instance2.getCurrentTicketNumber());
    }
}
