package com.demo.designModel.celvo;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lin.wang
 * @date 2020/07/22
 */
@Service
public class OperationStrategyService {
    Map<String, OperationStrategy> operationStrategyMap = Maps.newConcurrentMap();

    public OperationStrategyService(List<OperationStrategy> operationStrategyList) {
        operationStrategyList.forEach(operationStrategy -> {
            operationStrategyMap.put(operationStrategy.getType(), operationStrategy);
        });
    }

    public double operation(String type, double value1, double value2){
        double result = operationStrategyMap.get(type).disCount(value1, value2);
        System.out.println("结果： "  + result);
        return result;
    }
}
