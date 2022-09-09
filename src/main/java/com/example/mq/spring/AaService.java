package com.example.mq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author 钟金灿
 * @since 2022/9/9
 */
@Component
public class AaService {

    @Autowired
    BbService bbService;


    public void test(){
        System.out.println(bbService);
    };

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AaService aaService = (AaService) context.getBean("aaService");
        aaService.test();

    }
}
