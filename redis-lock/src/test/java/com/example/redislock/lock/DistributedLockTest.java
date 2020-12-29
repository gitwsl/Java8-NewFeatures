package com.example.redislock.lock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lin.wang
 * @date 2020/12/29
 */

@SpringBootTest
class DistributedLockTest {

    @Resource
    DistributedLock distributedLock;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /***
     * 1、先执行tryLock，锁300000， 则下面1002获取锁失败（这时一直重试），
     * 2、执行releaseLock，删除1001，则1002获取锁成功
     * @throws InterruptedException
     */
    @Test
    void tryLock() throws InterruptedException {
        boolean lockSuccess = distributedLock.tryLock("alert:data", "1001", 300000);
        System.out.println(lockSuccess ? "加锁成功" : "加锁失败");
        Thread.sleep(1000);
        lockSuccess = distributedLock.tryLock2("alert:data", "1002", 10000);
        System.out.println(lockSuccess ? "加锁成功" : "加锁失败");
//        distributedLock.releaseLock("alert:data", "1001");
        String userIDFromRedis = redisTemplate.opsForValue().get("alert:data");
        System.out.println(userIDFromRedis);
    }

    @Test
    void releaseLock() {
        distributedLock.releaseLock("alert:data", "1001");
    }
}