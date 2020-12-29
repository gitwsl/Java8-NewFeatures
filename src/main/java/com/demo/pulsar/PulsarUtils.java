package com.demo.pulsar;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author lin.wang
 * @date 2020/06/12
 */
@Slf4j
public class PulsarUtils {
    private static PulsarClient client;
    private final static int DEFAULT_PENDING_MESSAGES = 5_000;
    private final static int DEFAULT_SEND_TIMEOUT = 10;
    private static final ConcurrentHashMap<String, Producer<String>> TOPIC_STRING = new ConcurrentHashMap<>();

    static {
        try {
            init();
        } catch (PulsarClientException e) {
            log.error("创建Pulsar客户端出现异常", e);
            System.exit(1);
        }
    }

    private static void init() throws PulsarClientException {
        client = PulsarClient.builder()
                .serviceUrl("pulsar://10.0.1.155:6650")
                .build();
    }

    public static PulsarClient getPulsarClient() {
        return client;
    }


    public static void closePulsarClient() throws PulsarClientException {
        if (client != null) {
            client.close();
        }
    }

    public static Producer<String> getTopicProducer(String topic) throws PulsarClientException {
        Producer<String> producer = TOPIC_STRING.get(topic);
        if (!Optional.ofNullable(producer).isPresent()) {
            Producer<String> producerCreate = PulsarUtils.getPulsarClient().newProducer(Schema.STRING)
                    .maxPendingMessages(DEFAULT_PENDING_MESSAGES)
                    .blockIfQueueFull(true)
                    .sendTimeout(DEFAULT_SEND_TIMEOUT, TimeUnit.SECONDS)
                    .topic(topic)
                    .create();
            TOPIC_STRING.put(topic, producerCreate);
            return producerCreate;
        } else {
            return producer;
        }
    }

    public static MessageId send(String topic, String message) throws PulsarClientException {
        return getTopicProducer(topic).send(message);
    }

    public static CompletableFuture<MessageId> sendAsync(String topic, String message) throws PulsarClientException {
        return getTopicProducer(topic).sendAsync(message);
    }

    public static Consumer<byte[]> subscribeTopic(String group, SubscriptionType subscriptionType, String... topic) throws PulsarClientException {
        return PulsarUtils.getPulsarClient().newConsumer()
                .topics(Lists.newArrayList(topic))
                .subscriptionName(group)
                .ackTimeout(DEFAULT_SEND_TIMEOUT, TimeUnit.SECONDS)
                .subscriptionType(subscriptionType)
                .subscribe();

    }

    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://10.2.2.162:6650")
                .build();
        Consumer<byte[]> consumer = client.newConsumer()
                .topics(Arrays.asList(
                        "persistent://public/compute-metrics/metricLocalTest0101"))
                .subscriptionName("test-factory")
                .ackTimeout(10, TimeUnit.SECONDS)
                .subscriptionType(SubscriptionType.Shared)
                .subscribe();
        while (true) {
            Message<byte[]> msg = consumer.receive();
            String str = new String(msg.getData());
            consumer.acknowledge(msg);
            System.out.println("<================consumer===================>:" + str);
        }
    }
}
