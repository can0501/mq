package com.example.mq.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

//@Component
public class MyListener implements ApplicationListener<ApplicationEvent>, Ordered {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        //System.out.println("MyListener-》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
