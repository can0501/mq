package com.example.mq.mq.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//@RabbitListener(queues = "TestDirectQueue")
public class R {

    @Autowired
    @Qualifier("ewsRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(bindings =  {@QueueBinding(
            exchange = @Exchange("TestDirectExchange"),
            value = @Queue("TestDirectQueue"))
            })
//@RabbitListener(queues = {"TestDirectExchange"})
    public void sdsds(Message s ) throws IOException {

        System.out.println(s);
    }

}
