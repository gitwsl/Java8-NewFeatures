package com.example.rabbitmqdemo.server02;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/***
 * 消费者1接收到：测试发布订阅模型：0
 * 消费者1接收到：测试发布订阅模型：1
 * 消费者2接收到：测试发布订阅模型：1
 * 消费者1接收到：测试发布订阅模型：2
 * 消费者1接收到：测试发布订阅模型：3
 * 消费者2接收到：测试发布订阅模型：3
 * 消费者1接收到：测试发布订阅模型：4
 * 消费者1接收到：测试发布订阅模型：5
 * 消费者2接收到：测试发布订阅模型：5
 * 消费者1接收到：测试发布订阅模型：6
 * 消费者2接收到：测试发布订阅模型：7
 * 消费者1接收到：测试发布订阅模型：7
 * 消费者1接收到：测试发布订阅模型：8
 * 消费者1接收到：测试发布订阅模型：9
 * 消费者2接收到：测试发布订阅模型：9
 */
@Component
public class TopicReceiveListener {

    @RabbitListener(queues = "queue_topic1")
    public void receiveMsg1(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("消费者1接收到：" + message + "--" + new String(message.getBody()));
    }

    @RabbitListener(queues = "queue_topic2")
    public void receiveMsg2(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("消费者2接收到：" + message + "--" + new String(message.getBody()));
    }
}

