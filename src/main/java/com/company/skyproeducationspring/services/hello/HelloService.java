package com.company.skyproeducationspring.services.hello;

import org.springframework.stereotype.Service;

@Service
public class HelloService implements HelloServiceInterface {
    @Override
    public String hello() {
        return "Hello";
    }

    @Override
    public String hello(String username) {
        return "Hello, " + username;
    }
}
