package com.example.serviceb.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 添加注解声明是注册中心客户端
@EnableEurekaClient
// 实现不同子服务调用
@EnableFeignClients
public class TestBController {

    @Autowired
    private ServiceAFeignClient serviceAFeignClient;

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    @RequestMapping("call")
    public String call() {
        String result = serviceAFeignClient.TestAController();
        return "b to a 访问结果 ---" + result;
    }

    public String sayHelloFallback() {
        return "hi，菜虚坤，sorry，服务不可用";
    }
}
