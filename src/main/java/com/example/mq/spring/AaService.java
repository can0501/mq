package com.example.mq.spring;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author 钟金灿
 * @since 2022/9/9
 */
@Component
public class AaService implements BeanNameAware {

    @Autowired
    public BbService bbService;

    public String sd = "aa";


    public void test() {
        System.out.println(bbService);
        System.out.println(sd);
    }

    ;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AaService aaService = (AaService) context.getBean("aaService");
        aaService.test();


    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Aware接口方法: 发生在实例化后 能拿到beanName" + name);
    }
}
