package com.example.pagehelperdemo.service.impl;

import com.example.pagehelperdemo.dao.UserDao;
import com.example.pagehelperdemo.entity.UserInfoEntity;
import com.example.pagehelperdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lin.wang
 * @date 2020/07/03
 */
@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserInfoEntity> findAll() {
        return userDao.findAll();
    }
}
