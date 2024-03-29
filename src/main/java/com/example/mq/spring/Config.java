package com.example.mq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author 钟金灿
 * @since 2022/9/9
 */
@ComponentScan("com.example.mq.spring")
@EnableAspectJAutoProxy
//@EnableAsync
public class Config {
 }
