package com.example.serverzuui;

import com.example.serverzuui.filter.Filter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
// 添加注解声明是注册中心客户端
@EnableEurekaClient
public class ServerZuuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerZuuiApplication.class, args);
    }
    @Bean
    Filter tokenFilter() {
        return new Filter();
    }
}
