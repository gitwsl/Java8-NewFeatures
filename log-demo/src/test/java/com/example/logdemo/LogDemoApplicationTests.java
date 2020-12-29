package com.example.logdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class LogDemoApplicationTests {
    private final Logger logger= LoggerFactory.getLogger(LogDemoApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        logger.debug("输出DEBUG日志.......");
        logger.info("输出info日志.......");
        logger.error("输出error日志.......");
    }
    @Test
    public void test2(){
        log.debug("1输出DEBUG日志.......");
        log.info("1输出info日志.......");
        log.error("1输出error日志.......");
    }
}
