package com.example.mq;

import com.example.mq.mq.direct.R;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import java.util.StringTokenizer;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

}
