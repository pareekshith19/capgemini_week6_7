package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from BridgeLabz!";
    }

    @GetMapping("/helloWithParam")
    public String helloWithParam(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/hello/{name}")
    public String helloWithPath(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}