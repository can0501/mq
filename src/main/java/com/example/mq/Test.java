package com.example.mq;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author 钟金灿
 * @since 2021/7/3
 */
public class Test {
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();

    volatile int num = 0;


    @org.junit.jupiter.api.Test
    public void ada() {

        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();

        new Thread(() -> {

            while (0 == 0) {

                reentrantLock.lock();
                if (num >= 1) {
                    reentrantLock.notify();
                }
                num++;
                System.out.println("scz:" + num);
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                reentrantLock.unlock();
            }


        }, "生产者").start();

        new Thread(() -> {
            while (1 == 1) {
                reentrantLock.lock();
//                if (num
//                        <= 0) {
//                    try {
//                        condition.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
                num--;
                System.out.println("xfz:" + num);

                try {

                    condition.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                reentrantLock.unlock();
            }


        }, "消费者").start();


    }


    public Integer add1(Integer a) {
        a = a + 1;
        return a;
    }

    @org.junit.Test
    public void test233() {
        System.out.println(new BigDecimal("0.00").intValue());

    }

    @org.junit.Test
    public void test2333() {
        Lists.newArrayListWithExpectedSize(12);

    }

    @org.junit.Test
    public void test23333() {
        Short s = (short) 1;


        System.out.println(s.equals((short) 2));

        Thread thread = new Thread();


        HashMap hashMap = new HashMap();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        System.out.println(s.equals((short) 1));


    }

    @org.junit.Test
    public void sad(){


        Map<String, String> sddd = new LinkedHashMap<>();
        sddd.put("b", "123123");
        sddd.put("a", "123123");
        System.out.println(JSON.toJSONString(sddd));

        Map<String, String> sddd2 = new LinkedHashMap<>();
        sddd.put("a", "123123");
        sddd.put("b", "123123");

        System.out.println(JSON.toJSONString(sddd));

        A a = new A();
        a.setA("a");
        a.setB("b");
        System.out.println(JSON.toJSONString(a));

    }


    /**
     * @author 钟金灿
     * @since 2022/9/19
     */
    @Data
    public static class A   {
        private String b;
        private String a;
    }
}
