package com.demo.pulsar;

import com.alibaba.fastjson.JSONObject;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

import java.util.concurrent.TimeUnit;

/**
 * @author lin.wang
 * @date 2020/07/15
 */
public class Send {
    public static void main(String[] args) throws PulsarClientException, InterruptedException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://10.2.2.30:6650")
                .build();
        Producer<String> stringProducer = client.newProducer(Schema.STRING)
                .topic("POINT_DATA_TRANSFORMED_2_ALERT_RULE_1")
                //队列消息数量（特别注意，异步发送，超过最大队列则报 Producer send queue is full）
                .maxPendingMessages(5000)
                //send timeout
                .sendTimeout(10, TimeUnit.SECONDS)
                .create();
        JSONObject resultData = new JSONObject();
        JSONObject obj = new JSONObject();
//        for (int j = 0; j < 3; j++) {
//        obj.put("rid", "113");
            // GENERAL / FOCUS  / NORMAL
            obj.put("d", "FOCUS");
            obj.put("t", System.currentTimeMillis());
            obj.put("e", "temp1");
            obj.put("cid", 241);
            obj.put("rid", 113);
//        obj.put("k", 12);
            obj.put("a", "测试113");
        System.out.println(obj);
//        resultData.put("resultData", obj);
            for (int i = 0; i < 1; i++) {
                stringProducer.sendAsync(obj.toJSONString());
                System.out.println("<================sender===================>:" + "msg" + i);
            }
            Thread.sleep(10);
            resultData = new JSONObject();
            obj = new JSONObject();
//        obj.put("rid", "113");
            // GENERAL / FOCUS  / NORMAL
            obj.put("d", "GENERAL");
            obj.put("t", System.currentTimeMillis());
            obj.put("e", "temp1");
            obj.put("cid", 241);
            obj.put("rid", 113);
//        obj.put("k", 12);
            obj.put("a", "测试113");
//        resultData.put("resultData", obj);
            for (int i = 0; i < 1; i++) {
                stringProducer.sendAsync(obj.toJSONString());
                System.out.println("<================sender===================>:" + "msg" + i);
            }
            Thread.sleep(10);
            resultData = new JSONObject();
//            obj = new JSONObject();
////        obj.put("rid", "113");
//            // GENERAL / FOCUS  / NORMAL
//            obj.put("d", "FOCUS");
//            obj.put("t", System.currentTimeMillis());
//            obj.put("e", "temp1");
//            obj.put("cid", 241);
//            obj.put("rid", 113);
////        obj.put("k", 12);
//            obj.put("a", "测试113");
////        resultData.put("resultData", obj);
//            for (int i = 0; i < 1; i++) {
//                stringProducer.sendAsync(obj.toJSONString());
//                System.out.println("<================sender===================>:" + "msg" + i);
//            }
//            Thread.sleep(10);
//        }

    }
}
