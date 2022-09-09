package com.example.mq;

import com.example.mq.mq.direct.R;

import java.util.ArrayList;
import java.util.List;
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




}
