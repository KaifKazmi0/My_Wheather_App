package com.kaif.wheatherapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyDemo {
    @GetMapping("/greet")
    public String name(){
        return "hello from demo get";
    }

    @PostMapping("/greet")
    public String Nname(){
        return "hello from demo post";
    }
}
