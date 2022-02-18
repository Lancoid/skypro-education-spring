package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.services.hello.HelloServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloServiceInterface service;

    public HelloController(HelloServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public String hello() {
        return service.hello();
    }

    @GetMapping(path = "/hello")
    public String answerHello(@RequestParam("name") String username) {
        return service.hello(username);
    }
}
