package com.demo.designModel.celvo;

import org.springframework.stereotype.Service;

/**
 * @author lin.wang
 * @date 2020/07/22
 */
@Service
public class AddOperation implements  OperationStrategy{

    @Override
    public String getType() {
        return "+";
    }

    @Override
    public double disCount(double value1, double value2) {
        return value1 + value2;
    }
}
