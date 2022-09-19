package com.example.mq.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author 钟金灿
 * @since 2022/9/9
 */
@Aspect
@Component
public class MyAop {

    @Pointcut("execution (* com.example.mq.spring.AaService.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeAdvice() {
        System.out.println("前置通知");
    }


    @After("pointCut()")
    public void afterAdvice() {
        System.out.println("后置通知");
    }

    @Around("pointCut()")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("环绕 前置 ");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.out.println("环绕 后置");
    }
}
