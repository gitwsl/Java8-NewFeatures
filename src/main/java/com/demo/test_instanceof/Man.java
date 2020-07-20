package com.demo.test_instanceof;

import lombok.Data;

/**
 * @author lin.wang
 * @date 2020/07/07
 */
@Data
public class Man extends People {
    public Man(int age, String name) {
        super(age, name);
    }

    public Man() {
        super();
    }

    public static void main(String[] args) {
        System.out.println(new Man() instanceof People);
        System.out.println(new Woman() instanceof People);
    }
}
@Data
class Woman extends Man{
}