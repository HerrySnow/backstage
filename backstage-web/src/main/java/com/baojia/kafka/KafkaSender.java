/*
package com.baojia.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    */
/**
     * 发送消息到kafka,主题为test
     *//*

    public void sendTest(){
        kafkaTemplate.send("test","hello,kafka  "  + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }
}
*/
