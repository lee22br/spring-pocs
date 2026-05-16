package com.example.scope.request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trace")
public class TraceController {

    private final RequestTraceContext traceContext;

    // The Spring injects a PROXY of RequestTraceContext
    public TraceController(RequestTraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @GetMapping
    public String getCurrentTraceId() {
        return traceContext.getTraceId();
    }
}