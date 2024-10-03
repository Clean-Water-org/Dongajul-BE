package com.dongajul.sample.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SSE,
 * REST API,
 * SOCKET,
 * GRAPHQL
 * LISTENER (Subscriber, Consumer)
 */
@RequiredArgsConstructor
@RestController
public class SampleController {

    @GetMapping("/api/sample/test")
    public String test() {
        return "test success";
    }

    @PostMapping("/api/sample/login")
    public HttpStatus login() {
        return HttpStatus.OK;
    }
}



