package com.demo.test_instanceof;

import lombok.Data;

/**
 * @author lin.wang
 * @date 2020/07/07
 */
@Data
public class People {
    public People(int age, String name) {
    }

    public People() {
        super();
    }

    private int age;
    private String name;
}
