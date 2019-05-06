package com.example.springbootdocker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDockerController {

    @RequestMapping("/test")
    public String test(){
        return "hello docker";
    }
}
