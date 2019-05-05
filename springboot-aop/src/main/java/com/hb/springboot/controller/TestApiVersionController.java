package com.hb.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestApiVersionController {

    @RequestMapping(headers = "api-version=1", value = "/get")
    public String getV1() {
        return "hello world";
    }

    @RequestMapping(headers = "api-version=2", value = "/get")
    public String getV2() {
        return "hello tom";
    }

    @RequestMapping(headers = "api-version=3", value = "get")
    public String getV3() {
        return "hello jetty";
    }

    @RequestMapping("/v1/get")
    public String getV4() {
        return "hello v1";
    }

    @RequestMapping("/v2/get")
    public String getV5() {
        return "hello v2";
    }


}
