package com.demo.exp4j;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 * @author lin.wang
 * @date 2020/07/17
 * https://developer.aliyun.com/article/478118
 * 好用的Java数学表达式计算工具——Exp4j
 */
public class Exp4jDemo {
    // 包含变量的数学表达式
//    private final String FUNCTION = "x/y + (x+y)*z";
    private final String FUNCTION = "abs(((((x-y-z)))))";

    public Exp4jDemo() {

    }

    public void testFunction() {

        // 构建表达式，并声明变量定义
        ExpressionBuilder builder = new ExpressionBuilder(FUNCTION)
                .withVariableNames("x", "y", "z");

        // 以下两种方式也可以声明变量，并直接给变量进行赋值
        /*ExpressionBuilder.withVariable(String var,double value)
        ExpressionBuilder.withVariables(Map<String,Double> variables)*/

        try {

            // 生成计算对象
            Calculable calc = builder.build();

            // 设置变量的值
            calc.setVariable("x", 5);
            calc.setVariable("y", 3);
            calc.setVariable("z", 4);

            // 计算结果
            System.out.println(calc.calculate());

        } catch (UnknownFunctionException e) {
            e.printStackTrace();
        } catch (UnparsableExpressionException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        Exp4jDemo exp4jDemo = new Exp4jDemo();

        exp4jDemo.testFunction();


    }
}
