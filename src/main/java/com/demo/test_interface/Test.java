package com.demo.test_interface;

/**
 * @author lin.wang
 * @date 2020/06/27
 */
public class Test {
    public static void main(String[] args) {
        // 策略 模式
        Context context = new Context(new ChildA());
        context.operate();

        Context contextB = new Context(new ChildB());
        contextB.operate();
    }
}
