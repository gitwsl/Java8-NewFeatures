package com.demo.test_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author lin.wang
 * @date 2020/06/21
 */
@Getter
@AllArgsConstructor
public enum MockedFlagEnum {
    /**
     * not mocked flag data
     */
    NOT_MOCKED(1, "非模拟数据") {
        @Override
        void test() {
            System.out.println("NOT_MOCKED-" + this.getCode());
        }
    },
    /**
     * mocked flag
     */
    MOCKED(2, "模拟数据") {
        @Override
        void test() {
            System.out.println("MOCKED-" + this.getCode());
        }
    };
    private final int code;
    private final String message;

    abstract void test();

    public static void main(String[] args) {
        MockedFlagEnum mockedFlagEnum = MockedFlagEnum.valueOf("NOT_MOCKED");
        System.out.println(mockedFlagEnum);

        System.out.println(mockedFlagEnum.getCode() + "-" + mockedFlagEnum.getMessage());

        System.out.println("----------------------");
        MockedFlagEnum[] mockedFlagEnums = MockedFlagEnum.values();
        Stream.of(mockedFlagEnums).forEach(mockedFlagEnum1 -> {
            mockedFlagEnum1.test();
            System.out.println(mockedFlagEnum1.getCode() + "-" + mockedFlagEnum1.getMessage());
        });
    }
}
