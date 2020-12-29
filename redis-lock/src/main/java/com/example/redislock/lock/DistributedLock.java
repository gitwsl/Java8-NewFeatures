package com.example.redislock.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lin.wang
 * @date 2020/12/29
 */
@Component
public class DistributedLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 可以用于MQ,加锁不成功可以再次消费
     * 加锁
     * lockKey，redis的key
     * expireTime，过期时间，单位是毫秒
     * 注：setIfAbsent方法就使用了redis的setnx
     */
    public boolean tryLock(String lockKey, String userID, long expireTime) throws InterruptedException {
        long waitTime = 0;
        boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, userID,
                expireTime, TimeUnit.MILLISECONDS);
        if (success == true) {
            return success;
        } else {
            //如果加锁失败，循环重试加锁
            while (success != true && waitTime < 5000L) {
                success = redisTemplate.opsForValue().setIfAbsent(lockKey, userID,
                        expireTime, TimeUnit.MILLISECONDS);
//                sleep 100毫秒;
                Thread.sleep(100L);
                waitTime += 100L;
            }
        }

        return success;
    }

    /***
     * 用于不可以数据重新消费，又不想丢弃数据场景，
     * @param lockKey
     * @param userID
     * @param expireTime
     * @return
     * @throws InterruptedException
     */
    public boolean tryLock2(String lockKey, String userID, long expireTime) throws InterruptedException {
        long waitTime = 0;
        boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, userID,
                expireTime, TimeUnit.MILLISECONDS);
        if (success == true) {
            return success;
        } else {
            //如果加锁失败，一直重试
            while (true) {
                success = redisTemplate.opsForValue().setIfAbsent(lockKey, userID,
                        expireTime, TimeUnit.MILLISECONDS);
//                sleep 100毫秒;
                Thread.sleep(100L);
                if(success){
                    break;
                }
            }
        }

        return success;
    }

    /**
     * 释放锁
     * lockKey，redis的key
     */
    public void releaseLock(String lockKey, String userID) {
        String userIDFromRedis = redisTemplate.opsForValue().get(lockKey);
        if (userID.equals(userIDFromRedis)) {
            redisTemplate.opsForValue().getOperations().delete(lockKey);
        }
    }


    public static void main(String[] args) {

    }
}
