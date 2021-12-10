package com.example.rabbitmqdemo.server02;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitmqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendTopic")
    public String sendTopic() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                rabbitTemplate.convertSendAndReceive("exchange_topic", "topic.km.topic", "测试发布订阅模型：" + i);
            } else {
                rabbitTemplate.convertSendAndReceive("exchange_topic", "topic.km", "测试发布订阅模型：" + i);

            }
        }
        return "发送消息成功";
    }

}
