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
public enum MockedFlagEnum2 {
    /**
     * not mocked flag data
     */
    NOT_MOCKED,
    /**
     * mocked flag
     */
    MOCKED;

    public static void main(String[] args) {
        MockedFlagEnum2 mockedFlagEnum = MockedFlagEnum2.valueOf("NOT_MOCKED");
        System.out.println(mockedFlagEnum.ordinal());

        System.out.println("----------------------");
        MockedFlagEnum2[] mockedFlagEnums = MockedFlagEnum2.values();
        Stream.of(mockedFlagEnums).forEach(mockedFlagEnum1 -> {
            System.out.println(mockedFlagEnum1.ordinal() + "-" + mockedFlagEnum1);
        });

        if ("mocked".equalsIgnoreCase(MockedFlagEnum2.MOCKED.name())) {
            System.out.println("mocked~~~");
        }
    }
}
