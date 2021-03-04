

package com.example.mq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MqApplication {

    public static void main(String[] args) {
        System.out.println("localhost:8080/swagger-ui.html8");
        SpringApplication.run(MqApplication.class, args);
    }

}
