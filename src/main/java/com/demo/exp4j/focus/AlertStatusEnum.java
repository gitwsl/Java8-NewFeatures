package com.demo.exp4j.focus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lucius.fan
 * @date 2020/06/12
 */
@Getter
@AllArgsConstructor
public enum AlertStatusEnum {
    /**
     * normal alert status
     */
    NORMAL(1, "正常"),
    /**
     * focus alert status
     */
    FOCUS(2, "关注"),
    /**
     * general alert status
     */
    GENERAL(3, "一般"),
    /**
     * serious alert status
     */
    SERIOUS(4, "严重"),
    /**
     * critical alert status
     */
    CRITICAL(5, "紧急");

    private final int code;
    private final String message;
}
