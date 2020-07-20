package com.demo.exp4j.focus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lin.wang
 * @date 2020/06/12
 */
@Getter
@AllArgsConstructor
public enum LogicOperationEnum {
    /**
     * GREATER_THAN_OR_EQUAL_TO
     */
    GREATER_THAN_OR_EQUAL_TO(1, ">=") {
        @Override
        public boolean operate(double compareFirstNumber, double compareSecondNumber) {
            return Double.compare(compareFirstNumber, compareSecondNumber) >= 0;
        }
    },

    /**
     * GREATER_THAN
     */
    GREATER_THAN(2, ">") {
        @Override
        public boolean operate(double compareFirstNumber, double compareSecondNumber) {
            return Double.compare(compareFirstNumber, compareSecondNumber) > 0;
        }
    },

    /**
     * LESS_THAN_OR_EQUAL_TO
     */
    LESS_THAN_OR_EQUAL_TO(3, "<=") {
        @Override
        public boolean operate(double compareFirstNumber, double compareSecondNumber) {
            return Double.compare(compareFirstNumber, compareSecondNumber) <= 0;
        }
    },

    /**
     * LESS_THAN
     */
    LESS_THAN(4, "<") {
        @Override
        public boolean operate(double compareFirstNumber, double compareSecondNumber) {
            return Double.compare(compareFirstNumber, compareSecondNumber) < 0;
        }
    },

    /**
     * EQUAL_TO
     */
    EQUAL_TO(5, "=") {
        @Override
        public boolean operate(double compareFirstNumber, double compareSecondNumber) {
            return Double.compare(compareFirstNumber, compareSecondNumber) == 0;
        }
    };

    private final int code;
    private final String message;

    /**
     * target data operate threshold
     *
     * @param compareFirstNumber  the first data number
     * @param compareSecondNumber the second data number
     * @return return the target operate result
     */
    public abstract boolean operate(double compareFirstNumber, double compareSecondNumber);
}
