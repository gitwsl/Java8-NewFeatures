package com.demo.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lin.wang
 * @date 2020/07/01
 * @Validated 可以加组  (一般使用这个)
 * @Valid 只能验证未加组的
 */
@RestController
@RequestMapping("valid/user/")
@Slf4j
public class HelloController {
    @Value("${server.port}")
    public String serverPort;

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public String hello(@Validated @RequestBody User user) throws Exception {
        log.info("user={}", user);

        return "success" + serverPort;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@Validated({User.Insert.class}) @RequestBody User user) throws Exception {
        log.info("user={}", user);

        return "success";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Validated({User.Update.class, User.Delete.class}) @RequestBody User user, BindingResult bindingResult) throws Exception  {
        log.info("user={}", user);
        //若不符合约束
        if (bindingResult.hasErrors()) {
            //获取不符合约束时，自定义的异常信息
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        return "success";
    }
}