package com.example.springdatajpa.controller;

import com.example.springdatajpa.entity.User;
import com.example.springdatajpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class UserController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello springboot!";
    }

    @Autowired
    UserService userService;

    /**
     * @auther: zhangyingqi
     * @date: 17:37 2018/8/27
     * @param: [request, user]
     * @return: org.springframework.ui.ModelMap
     * @Description: 用户保存&更新
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(User user){
        try {
            if(StringUtils.isEmpty(user.getId())){
                user.setId(String.valueOf((int)Math.random() * 1000));
            }else{
                user.setUpdateDate(new Date());
            }
            userService.save(user);
            return "保存成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "保存失败";
        }
    }

    /**
     * @auther: zhangyingqi
     * @date: 17:47 2018/8/27
     * @param: [id]
     * @return: org.springframework.ui.ModelMap
     * @Description: 删除用户
     */
    @RequestMapping(value="/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(String id){
        try {
            User user = userService.findById(id);
            if(user==null){
                return "找不到该用户";
            }
            userService.delete(user);
            log.info("删除成功");
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
    }

    /**
     * @auther: zhangyingqi
     * @date: 17:47 2018/8/27
     * @param: [request]
     * @return: java.lang.String
     * @Description: 查询用户列表
     */
    @RequestMapping(value="/list")
    public String view(HttpServletRequest request){
        List<User> list = userService.findAll();
        request.setAttribute("list", list);
        log.info("返回列表页面");
        return list.toString();
    }
}
