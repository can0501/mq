

package com.example.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableRabbit
@SpringBootApplication
@RestController
@RequestMapping("a")
@Slf4j
public class MqApplication {

    public static void main(String[] args) {
        System.out.println("localhost:8080/swagger-ui.html8");
        log.debug("debug");
        log.info("info");

        log.warn("warn");
        log.error("error");
        SpringApplication.run(MqApplication.class, args);
    }

//    @Value("${abc}")
    private String abc;
    @RequestMapping(value = "a", method = RequestMethod.GET)
    public String a() {
        System.out.println(1);


            log.debug("debug");
            log.info("info");

            log.warn("warn");
            log.error("error");
        return "sd";
    }




}
