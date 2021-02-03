package com.example.rabbit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    void contextLoads() {
    }

    @Test
    void testSender() {
        System.out.println("============================================");
        sender.send();
        System.out.println("============================================");
    }

}
