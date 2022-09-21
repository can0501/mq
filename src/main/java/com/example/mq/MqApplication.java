

package com.example.mq;

import com.example.mq.kafka.KafkaProvider;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

//@EnableRabbit
@SpringBootApplication
//@RestController
//@RequestMapping("a")
public class MqApplication {

    public static void main(String[] args) {
        System.out.println("localhost:8080/swagger-ui.html8");
        SpringApplication.run(MqApplication.class, args);
    }

    @RequestMapping(value = "a", method = RequestMethod.GET)
    public String a() {
        ReentrantLock reentrantLock = new ReentrantLock();
        System.out.println(1);
        return "sad";
    }


    @RequestMapping(value = "b", method = RequestMethod.GET)
    public String b() {
        System.out.println(1);
        return "sad";
    }




}
