package com.example.springdatajpa.service;

import com.example.springdatajpa.entity.User;

import java.util.List;

/**
 * @author lin.wang
 * @date 2020/07/03
 */
public interface UserService {
    User save(User user);

    User findById(String id);

    void delete(User user);

    List<User> findAll();
}
