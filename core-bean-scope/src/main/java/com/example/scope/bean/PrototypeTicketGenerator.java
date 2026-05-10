package com.example.scope.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // Forces Spring to create a new instance every time
public class PrototypeTicketGenerator {

    private int ticketNumber = 0;

    public int generateTicket() {
        return ++ticketNumber;
    }

    public int getCurrentTicketNumber() {
        return ticketNumber;
    }
}
