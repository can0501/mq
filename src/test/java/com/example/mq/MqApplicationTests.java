package com.example.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MqApplicationTests {


    @Autowired
//    @Qualifier("ewsRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

//    @Test
//    private void send() {
//        //(交换机,routingKey,消息内容)
//        rabbitTemplate.convertAndSend("myDirectExchange", "mine.direct", "this is a message");
//    }

    @Test
    void contextLoads() {
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", "abc");
    }

//    @Test
//    void sdad() {
//        rabbitTemplate. ("TestDirectExchange", "TestDirectRouting", "abc");
//    }


    @Autowired
   private   String asd;

    @Test
    void as() {
        System.out.println(asd
        );
    }

}
