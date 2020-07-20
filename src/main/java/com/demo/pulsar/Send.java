package com.demo.pulsar;

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
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://10.0.3.97:6650")
                .build();
        Producer<String> stringProducer = client.newProducer(Schema.STRING)
                .topic("my_topic")
                //队列消息数量（特别注意，异步发送，超过最大队列则报 Producer send queue is full）
                .maxPendingMessages(5000)
                //send timeout
                .sendTimeout(10, TimeUnit.SECONDS)
                .create();
        for(int i = 0;i<100;i++){
            stringProducer.sendAsync("msg"+i);
            System.out.println("<================sender===================>:"+"msg"+i);
        }
    }
}
