package com.pyxicm.prod.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin // Optional if you're adding global CORS
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Pyxicm!";
    }
}
