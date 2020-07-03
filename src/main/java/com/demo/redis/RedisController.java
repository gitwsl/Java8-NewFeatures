package com.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lin.wang
 * @date 2020/07/02
 */

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @Autowired
    private RedisOperator redis;

    @RequestMapping("/test")
    public String test() {
        strRedis.opsForValue().set("json:user", "test");

        return "success";
    }

    @RequestMapping("/getJsonList")
    public String getJsonList() {
        redis.set("json:info:userlist", "getJsonList", 2000);

        String userListJson = redis.get("json:info:userlist");
        return userListJson;
    }
}
