package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.services.hello.HelloServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    private final HelloServiceInterface service;

    public HelloController(HelloServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("text", service.hello());

        return "hello/index";
    }

    @GetMapping(path = "/hello")
    public String answerHello(@RequestParam("name") String username, Model model) {
        model.addAttribute("text", service.hello(username));

        return "hello/index";
    }
}
