package com.demo.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTest1 {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String  kafaTopic;

    @GetMapping("/send/{input}")
    public String sendFoo(@PathVariable String input) {
        System.out.println("========================");
        System.out.println(kafkaTemplate);
        this.kafkaTemplate.send(kafaTopic, input);
        return input;
    }

}
