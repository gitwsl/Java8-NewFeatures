//package com.demo.kafka.kafka;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class KafkaConsumer {
//
//    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group}")
//    public void data(ConsumerRecord record) {
//        System.out.println("group-1");
//        log.info("topic is {}, offset is {}, value is {} ", record.topic(), record.offset(), record.value());
//    }
//
//    @KafkaListener(topics = "${kafka.topic}", groupId = "group-2")
//    public void data2(ConsumerRecord record) {
//        System.out.println("group-2");
//        log.info("topic is {}, offset is {}, value is {} ", record.topic(), record.offset(), record.value());
//    }
//}
