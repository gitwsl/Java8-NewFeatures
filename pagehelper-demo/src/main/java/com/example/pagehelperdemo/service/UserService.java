package com.example.pagehelperdemo.service;

import com.example.pagehelperdemo.entity.UserInfoEntity;

import java.util.List;

/**
 * @author lin.wang
 * @date 2020/07/03
 */
public interface UserService {
    List<UserInfoEntity> findAll();
}
