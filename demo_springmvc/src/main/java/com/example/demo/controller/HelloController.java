package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    // The value returned by this method will be written directly to the HTTP response body.
    @ResponseBody
    public String hello() {
        return "Hello Spring MVC !";
    }
}
