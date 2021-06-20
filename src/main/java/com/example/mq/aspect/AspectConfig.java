package com.example.mq.aspect;

import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Component
public class AspectConfig {


    @Pointcut("execution(public * com.example.mq.controller.asdasdasd.*(..)))")
    public void BrokerAspect(){
        System.out.println("定义一个切点，在切入点表达式可以引用");
    }


    /**
     * @description  在连接点执行之前执行的通知
     */
    @Before("BrokerAspect()")
    public void doBeforeGame(JoinPoint joinPoint){
        if (joinPoint != null) {
            ArrayList list = new ArrayList();
            LinkedList linkedList = new LinkedList();
            HashMap map = new HashMap();

            System.out.println(Lists.newArrayList(joinPoint.getArgs()).stream().findFirst().map(e->e.toString()).orElse("没有参数"));
        }
        System.out.println("前置通知！");
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("BrokerAspect()")
    public void doAfterGame(){
        System.out.println("后置通知！");
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("BrokerAspect()")
    public void doAfterReturningGame(){
        System.out.println("返回通知");
    }

    /**
     * @description  在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowingGame(){
        System.out.println("异常通");
    }


    public static void main(String[] arg){
        Runnable t1=new MyThread();
        new Thread(t1,"t1").start();
        new Thread(t1,"t2").start();
     }

}
class MyThread implements Runnable {

    private Lock lock=new ReentrantLock();
    public void run() {
        lock.lock();
        try{
            for(int i=0;i<5;i++)
                System.out.println(Thread.currentThread().getName()+":"+i);
        }finally{
            lock.unlock();
//            lock.lockInterruptibly();
        }}

}
