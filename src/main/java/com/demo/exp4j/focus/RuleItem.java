package com.demo.exp4j.focus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lucius.fan
 * @date 2020/06/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleItem {
    private String operation;
    private Double threshold;
    private String metricCode;
    private String pointCode;
}
