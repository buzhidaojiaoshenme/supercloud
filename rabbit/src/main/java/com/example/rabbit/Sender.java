package com.example.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello: " + LocalDateTime.now();
        System.out.println("sender发送的: " + context);
        rabbitTemplate.convertAndSend("hello" + context);
    }

}

