package com.demo.test_jsontoentity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lin.wang
 * @date 2020/06/21
 */
@Slf4j
public class TestJsonToEntity {
    public static void main(String[] args) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // 两种转换成json字符串的方式
            String dataStr1 = JSON.toJSONString(ResultEntity.getFailResultEntity(-1, "1111"));
            String dataStr2 = objectMapper.writeValueAsString(ResultEntity.getFailResultEntity(-1, "1111"));
            System.out.println(dataStr1);
            System.out.println(dataStr2);

            // 两种把json字符串转实体的方式
            // 第一种方式
            ResultEntity resultEntity = JSON.parseObject(dataStr1, ResultEntity.class);
            System.out.println(resultEntity);
            resultEntity = JSON.parseObject(dataStr2, ResultEntity.class);
            System.out.println(resultEntity);

            // 第二种方式
            resultEntity = objectMapper.readValue(dataStr1, ResultEntity.class);
            System.out.println(resultEntity);
            resultEntity = objectMapper.readValue(dataStr2, ResultEntity.class);
            System.out.println(resultEntity);
        } catch (Exception ex) {
            log.error("sorry, exception message is {}", ex.getMessage());
        }

    }
}
