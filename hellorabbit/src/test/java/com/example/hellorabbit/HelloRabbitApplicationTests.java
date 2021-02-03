package com.example.hellorabbit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloRabbitApplicationTests {

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
