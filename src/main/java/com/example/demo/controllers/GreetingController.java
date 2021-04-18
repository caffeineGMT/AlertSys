package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
//    @CrossOrigin(origin = "*")
    @GetMapping("/hello1")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "hello" + name;
    }
}
