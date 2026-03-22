package net.javaguide.springboot_rest_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-arenaho")
    public String helloWorld() {
        return "Hello, ARENAHO!";
    }
}
