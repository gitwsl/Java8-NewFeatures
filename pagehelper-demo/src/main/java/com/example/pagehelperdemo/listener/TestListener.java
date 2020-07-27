package com.example.pagehelperdemo.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author lin.wang
 * @date 2020/07/26
 *
 * 启动springboot后立即执行的一部分代码。
 */
@Component
public class TestListener implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("启动springboot后立即执行的一部分代码。");
    }
}
