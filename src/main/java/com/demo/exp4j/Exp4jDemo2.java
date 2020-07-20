package com.demo.exp4j;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 * @author lin.wang
 * @date 2020/07/17
 */
public class Exp4jDemo2 {
    // 包含变量的数学表达式
//    private final String FUNCTION = "x/y + (x+y)*z";
    private final String FUNCTION = "abs(((((x-y-z)))))";

    public Exp4jDemo2() {

    }

    public void testFunction(String mainPointCode, String ...switchgearPointCode) {


    }


    public static void main(String[] args) {

        Exp4jDemo2 exp4jDemo = new Exp4jDemo2();

        exp4jDemo.testFunction("electric_abinet", "switchgear_01");

    }
}
