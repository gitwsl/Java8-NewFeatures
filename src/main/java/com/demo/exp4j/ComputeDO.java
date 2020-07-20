package com.demo.exp4j;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author lucius.fan
 * @date 2020/07/17
 */
@Data
public class ComputeDO {
    private final String expression = "abs((x-y)/y)";
    private String resultKey = "tongmuxian_";
    private List<ExplainDO> explainList;


    public static ComputeDO makeRule(String mainPointCode, String pointCodes) {
        ComputeDO computeDO = new ComputeDO();
        computeDO.setResultKey(makeRuleKey(pointCodes));

        ExplainDO explainDO = new ExplainDO();
        String explain = "x=" + mainPointCode;
        explainDO.setExplain(explain);

        ExplainDO explainDOy = new ExplainDO();
        String explainy = "y=" + pointCodes;
        explainDOy.setExplain(explainy);

        computeDO.setExplainList(Lists.newArrayList(explainDO, explainDOy));
        return computeDO;
    }

    public static String makeRuleKey(String pointCodes) {
        return "tongmuxian_" + pointCodes;
    }
}
