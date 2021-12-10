package com.example.rabbitmqdemo.server;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
public class Producer {
    @Autowired
    RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/send")
    public String makeOrder(String msg) {
        msg += new Date();
        log.info("发送消息：{}", msg);
        String exchangeName = "nuctech_exchange_test1";
        String routingKey = "nuctech_routing_key1";
        String queue = "queue031";
//        String queue = "queue02";
        //注意： 接收和发送的路由模式要一致，这里使用topic, 接收端也要配置 type = ExchangeTypes.TOPIC？？但是这里topic还是发送给了指定的queue,不知道问什么没有模糊匹配上（所以最好还是使用server02方式）
//        rabbitAdmin.declareExchange(new DirectExchange(exchangeName));
        rabbitAdmin.declareExchange(new TopicExchange(exchangeName));
        rabbitAdmin.declareQueue(new Queue(queue));
        rabbitAdmin.declareBinding(new Binding(queue, Binding.DestinationType.QUEUE, exchangeName, routingKey, null));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, JSONObject.toJSONString(msg));
        return "发送消息" + msg;
    }
}
