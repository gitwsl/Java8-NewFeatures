package com.demo.exp4j.focus;

import com.alibaba.fastjson.JSON;
import com.demo.exp4j.ComputeDO;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author lucius.fan
 * @date 2020/07/16
 */
public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println(generateCalcRule("pointCode-1", "pointCode-2", "pointCode-3", "pointCode-5", "pointCode-4"));
//        System.out.println("=============================================================");
        final TypeRuleItem normalRule = genarateNormalRule("pointCode-1", "pointCode-2", "pointCode-3", "pointCode-5", "pointCode-4");
//        System.out.println(normalRule);
//        System.out.println("=============================================================");
        final TypeRuleItem focusRule = getFocusRule("pointCode-1", "pointCode-2", "pointCode-3", "pointCode-5", "pointCode-4");
//        System.out.println(focusRule);
        System.out.println("=============================================================");

        final ArrayList<TypeRuleItem> ruleList = Lists.newArrayList(normalRule, focusRule);
        System.out.println(JSON.toJSONString(ruleList));

    }

    private static String generateCalcRule(String mainPointCode, String... pointCodes) {
        List<ComputeDO> computeDOList = Lists.newArrayList();
        Stream.of(pointCodes).forEach(pointCode -> {
            computeDOList.add(ComputeDO.makeRule(mainPointCode, pointCode));
        });
        return JSON.toJSONString(computeDOList);
    }

    private static TypeRuleItem genarateNormalRule(String mainPointCode, String... pointCodes) {
        List<List<RuleItem>> mainRuleList = Lists.newArrayList();
        Stream.of(pointCodes).forEach(pointCode -> {
            RuleItem ruleItem1 = RuleItem.builder()
                    .metricCode(mainPointCode)
                    .pointCode(mainPointCode)
                    .operation(LogicOperationEnum.LESS_THAN.getMessage())
                    .threshold(30.0).build();

            List<RuleItem> ruleItemList = Lists.newArrayList();
            ruleItemList.add(ruleItem1);
            RuleItem ruleItem = RuleItem.builder()
                    .metricCode(ComputeDO.makeRuleKey(pointCode))
                    .pointCode(ComputeDO.makeRuleKey(pointCode))
                    .operation(LogicOperationEnum.LESS_THAN_OR_EQUAL_TO.getMessage())
                    .threshold(0.5).build();
            ruleItemList.add(ruleItem);
            mainRuleList.add(ruleItemList);
        });
        final TypeRuleItem typeRuleItem = TypeRuleItem.builder().ruleType(AlertStatusEnum.NORMAL.getMessage()).ruleItemList(mainRuleList).build();

        return typeRuleItem;
    }

    private static TypeRuleItem getFocusRule(String mainPointCode, String... pointCodes) {
        TypeRuleItem typeRuleItem = TypeRuleItem.builder()
                .ruleType(AlertStatusEnum.FOCUS.name())
                .ruleItemList(getFocusRuleItemList(mainPointCode, pointCodes))
                .build();
        return typeRuleItem;
    }

    private static List<List<RuleItem>> getFocusRuleItemList(String mainPointCode, String... pointCodes) {
        List<List<RuleItem>> ruleItemList = Lists.newArrayList();

        List<RuleItem> ruleItemSecondList1 = Lists.newArrayList();
        RuleItem ruleItem = RuleItem.builder()
                .operation(LogicOperationEnum.GREATER_THAN_OR_EQUAL_TO.name())
                .threshold(30.0)
                .pointCode(mainPointCode)
                .metricCode(mainPointCode)
                .build();
        ruleItemSecondList1.add(ruleItem);
        ruleItemList.add(ruleItemSecondList1);

        List<RuleItem> ruleItemSecondList2 = Lists.newArrayList();
        RuleItem ruleItem1 = RuleItem.builder()
                .operation(LogicOperationEnum.LESS_THAN.name())
                .threshold(30.0)
                .pointCode(mainPointCode)
                .metricCode(mainPointCode)
                .build();
        ruleItemSecondList2.add(ruleItem1);

        Stream.of(pointCodes).forEach(pointCode -> {
            RuleItem item = RuleItem.builder()
                    .operation(LogicOperationEnum.GREATER_THAN.name())
                    .threshold(0.5)
                    .pointCode(ComputeDO.makeRuleKey(pointCode))
                    .metricCode(ComputeDO.makeRuleKey(pointCode))
                    .build();
            ruleItemSecondList2.add(item);
        });
        ruleItemList.add(ruleItemSecondList2);
        return ruleItemList;
    }
}
