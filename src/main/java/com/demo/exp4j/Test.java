package com.demo.exp4j;

import com.alibaba.fastjson.JSON;
import com.demo.exp4j.ComputeDO;
import com.google.common.collect.Lists;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author lucius.fan
 * @date 2020/07/16
 */
public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(generateCalcRule("1", "2", "3", "5", "4"));

//        ExpressionBuilder expressionBuilder = new ExpressionBuilder("(x | y)");
//        final Calculable calculable = expressionBuilder.withVariable("x", 5)
//                .withVariable("y", 10)
//                .build();
//        final double calculate = calculable.calculate();
//        System.out.println(calculate);
    }

    private static String generateCalcRule(String mainPointCode, String... pointCodes) {
        List<ComputeDO> computeDOList = Lists.newArrayList();
        Stream.of(pointCodes).forEach(pointCode -> {
            computeDOList.add(ComputeDO.makeRule(mainPointCode, pointCode));
        });
        return JSON.toJSONString(computeDOList);
    }

}
