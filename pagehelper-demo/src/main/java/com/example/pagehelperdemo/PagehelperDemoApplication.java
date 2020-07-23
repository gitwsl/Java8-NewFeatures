package com.example.pagehelperdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.pagehelperdemo.dao")
public class PagehelperDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagehelperDemoApplication.class, args);
    }

}
