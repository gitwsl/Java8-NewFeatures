package com.demo.exp4j.focus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lucius.fan
 * @date 2020/06/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeRuleItem {
    private String ruleType;
    private List<List<RuleItem>> ruleItemList;
}
