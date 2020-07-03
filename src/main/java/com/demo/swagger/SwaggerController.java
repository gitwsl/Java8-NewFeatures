package com.demo.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date 2020/07/02
 *
 *
 * 一篇文章带你搞懂 Swagger 与 SpringBoot 整合
 * 原创 李增光 企鹅杏仁技术站 2019-09-26
 * https://mp.weixin.qq.com/s?src=11&timestamp=1593653135&ver=2435&signature=V2KDyhCwgKgz1eYzFmFTEAOFaGiTZedakGvj1MWdx25qAA2oFofmTMiqIc90GJaDxe*FEYmlgg2HboLnNODDyfEyZR0LUVYKj5aiBSIfkO95BZt7dJHizjwleMaW5Qr7&new=1
 */
@RestController
@RequestMapping("swagger/user/")
@Slf4j
@Api(description = "SwaggerController管理")
public class SwaggerController {
    @Value("${server.port}")
    public String serverPort;

    @ApiOperation(value = "hello 测试", notes = "hello 测试～～～～～")
    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public String hello(@Validated @RequestBody User user) throws Exception {
        log.info("user={}", user);

        return "success" + serverPort;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@Validated @RequestBody User user) throws Exception {
        log.info("user={}", user);

        return "success";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Validated @RequestBody User user, BindingResult bindingResult) throws Exception  {
        log.info("user={}", user);
        //若不符合约束
        if (bindingResult.hasErrors()) {
            //获取不符合约束时，自定义的异常信息
            throw new Exception(bindingResult.getFieldError().getDefaultMessage());
        }
        return "success";
    }
}