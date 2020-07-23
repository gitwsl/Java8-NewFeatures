package com.demo.designModel.celvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lin.wang
 * @date 2020/07/22
 *
 * http://localhost:8080/strategy/test/+?value1=1&value2=3
 */
@RestController
@RequestMapping("/strategy")
public class Test {
    @Autowired
    private OperationStrategyService operationStrategyService;

    @RequestMapping("/test/{symbol}")
    public double operation(@PathVariable(value = "symbol") String symbol, @RequestParam(value = "value1") double value1, double value2) {
        double result = operationStrategyService.operation(symbol, value1, value2);
        System.out.println((value1 + symbol + value2) + "= " + result);
        return result;
    }

    public static void main(String[] args) {

    }
}
