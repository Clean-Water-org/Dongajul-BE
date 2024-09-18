package com.dongajul.sample.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SSE,
 * REST API,
 * SOCKET,
 * GRAPHQL
 * LISTENER (Subscriber, Consumer)
 */
@RestController
public class SampleController {
    @GetMapping("/api/sample/test")
    public String test() {
        return "test success";
    }
}



