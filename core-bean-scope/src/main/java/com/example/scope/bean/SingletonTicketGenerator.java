package com.example.scope.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // Optional: Spring uses Singleton by default
public class SingletonTicketGenerator {

    private int ticketNumber = 0;

    public int generateTicket() {
        return ++ticketNumber;
    }

    public int getCurrentTicketNumber() {
        return ticketNumber;
    }
}
