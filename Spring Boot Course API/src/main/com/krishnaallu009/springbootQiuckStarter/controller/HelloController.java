package com.krishnaallu009.springbootQiuckStarter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This annotation indicates that this class is a RESTful controller, meaning it will handle HTTP requests and return responses in a web application.
@RestController
public class HelloController {

    // This annotation maps HTTP requests to the /hello URL to the sayHi() method.
    // The @RequestMapping maps only GET requests by default.
    @RequestMapping("/hello")
    public String sayHi() {
        return "Hi All, Welcome to Spring Boot Application";
    }
}
