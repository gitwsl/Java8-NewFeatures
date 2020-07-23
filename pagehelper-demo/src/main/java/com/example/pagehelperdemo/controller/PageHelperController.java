package com.example.pagehelperdemo.controller;

import com.example.pagehelperdemo.entity.UserInfoEntity;
import com.example.pagehelperdemo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description UserInfoController
 * @Author Sans
 * @CreateTime 2019/6/8 16:27
 *
 * springboot+springdatajpa+pagehelper 分页不生效, 原因是springdatajpa不支持，有自己的分页插件
 * 下面改成mybatis
 * http://localhost:8080/page/getInfoListPage
 */
@RestController
@RequestMapping("/page")
public class PageHelperController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询全部数据
     * @Author Sans
     * @CreateTime 2019/6/8 16:37
     * @Return IPage<UserInfoEntity> 分页数据
     */
    @RequestMapping("/getInfoListPage")
    public PageInfo<UserInfoEntity> getInfoListPage(){
        PageHelper.startPage(1,3);
//        List<UserInfoEntity> list = new ArrayList<>();
//        for (int i =0; i < 20; i ++){
//            list.add(UserInfoEntity.builder().age(i).name(""+i).build());
//        }
        List<UserInfoEntity> list = userService.findAll();
        PageInfo<UserInfoEntity> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }
}