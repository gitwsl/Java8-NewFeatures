package com.demo.test_interface;

/**
 * @author lin.wang
 * @date 2020/06/27
 */
public class Context {
    private Parent strategy;

    //构造函数，要你使用哪个妙计
    public Context(Parent strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Parent strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        this.strategy.operate();
    }
}