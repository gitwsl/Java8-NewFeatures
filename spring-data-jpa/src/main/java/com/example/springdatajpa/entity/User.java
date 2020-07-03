package com.example.springdatajpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author lin.wang
 * @date 2020/07/03
 */

@Entity
@Table(name = "user1")
@Data
public class User extends BaseEntity {
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "age")
    private int age;

}
