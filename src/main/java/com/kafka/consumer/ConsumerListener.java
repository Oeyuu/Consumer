package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    @KafkaListener(groupId = "c1", topics = "test1",   containerFactory = "rawKafkaListenerContainerFactory")
    public void listen(String in) {
        System.out.println(in);
    }
}
