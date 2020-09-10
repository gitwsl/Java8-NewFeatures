package com.example.servicea.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAController {
    @Value("${server.port}")
    private String port;

    @Value("${config.testValue}")
    private String testValue;

    @RequestMapping("testA")
    public String TestAController() {
        return "Hello,SpringCloud for TestA~~~~" + port + "  test config value: " + testValue;
    }
}
