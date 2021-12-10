package com.example.rabbitmqdemo.server;


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "queue011"), exchange =@Exchange(value = "nuctech_exchange_test1", type = ExchangeTypes.TOPIC), key = "#nuctech_routing_ke#")})
    public void getCheckImage(Message message, Channel channel) {
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            String body = new String(message.getBody());
            log.info("queue01, message:{}, body:{}",  message, body);
        } catch (Exception e){
            log.error("queue01, message:{}",  message);
        }
    }

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "queue021"), exchange =@Exchange(value = "nuctech_exchange_test1", type = ExchangeTypes.TOPIC), key = "#nuctech_routing#")})
    public void getCheckImage2(Message message, Channel channel) {
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            String body = new String(message.getBody());
            log.info("queue02, message:{}, body:{}",  message, body);
        } catch (Exception e){
            log.error("queue02, message:{}",  message);
        }
    }

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "queue031"), exchange =@Exchange(value = "nuctech_exchange_test1", type = ExchangeTypes.TOPIC), key = "#nuctech_routing_k#")})
    public void getCheckImage3(Message message, Channel channel) {
        try{
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            String body = new String(message.getBody());
            log.info("queue03, message:{}, body:{}",  message, body);
        } catch (Exception e){
            log.error("queue03, message:{}",  message);
        }
    }
}
