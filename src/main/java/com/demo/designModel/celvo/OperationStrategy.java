package com.demo.designModel.celvo;

/**
 * @author lin.wang
 * @date 2020/07/22
 */
public interface OperationStrategy {
    String getType();

    double disCount(double value1, double value2);

}
