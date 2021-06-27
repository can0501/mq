package com.example.mq.并发编程.线程;

import com.example.mq.并发编程.线程池.线程池的test;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 钟金灿
 * @since 2021/6/26
 */
public class ThreadTest {
    @Test
    public void a() throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();

        Thread thread = new Thread(() -> {
            synchronized (o1) {
                try {

                System.out.println(1);
//                    while (true) {
//
//                    }
                    Thread.sleep(500L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                thread.join();
                System.out.println(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread.start();

//        Thread.s
//        Thread thread3 = new Thread(() -> {
//            synchronized (o1) {
//                try {
//                    o1.wait();
//
//                    System.out.println("3");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread3.start();
//
//        Thread.sleep(50L);
//        Thread thread2 = new Thread(() -> {
//            synchronized (o1) {
//                try {
//                    o1.notifyAll();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread2.start();
//
//
        thread2.join();

    }

    @Test
    public void asdsad() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(40000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println(thread.getState());

        thread.join(2L);
        Thread.sleep(222L);
        System.out.println(thread.getState());

    }

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Object o2 = new Object();
        Thread thread = new Thread(() -> {
            synchronized (o) {
                System.out.println(1111);
                try {
                    while (true) {

                    }

//                    Thread.sleep(500L);
//                    while (true) {
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                System.out.println(1111);
                try {
                    while (true) {

                    }
                    //                    www.baidu.com
//                    线程池www.baidu.com
//                    Thread.sleep(500L);
//                    while (true) {
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        System.out.println(thread.getState());
        thread.start();
        Thread.sleep(50L);
        System.out.println(thread.getState());


        Thread.sleep(60L);
        thread.run();
//        synchronized (o) {
//            o.notifyAll();
//        }

        thread2.start();
        Thread.sleep(60L);
//0

        System.out.println(thread2.getState());

//        ConcurrentHashMap
    }
}
