package com.example.serviceb.controller;

import org.springframework.stereotype.Service;

@Service
public class ServiceACallbackClient implements ServiceAFeignClient {

    @Override
    public String TestAController() {
        return "hi，菜虚坤，sorry，服务不可用";
    }
}
