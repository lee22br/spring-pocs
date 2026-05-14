package com.example.bean.post.processor.service;

import com.example.bean.post.processor.Trackable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements Trackable {

    private String systemId;

    public void setSystemId(String id) {
        this.systemId = id;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public String processPayment() {
        return "Payment processed by system ID: " + systemId;
    }
}
