package com.example.mq.线程池;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class newCachedThreadPool {

    public static void main(String[] args) {
        cacheThread();
        System.out.println("--------------------------");
        noThread();
//        long ss = System.currentTimeMillis();
//
//        System.out.println(String.format("time:%s", System.currentTimeMillis() - ss));


    }

    private static void cacheThread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long ss = System.currentTimeMillis();
        AtomicInteger count= new AtomicInteger();
//        PriorityQueue<Long> queue;

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {

                try {
                    new Thread().sleep(1000L);
                    System.out.println(String.format("【Thread】time:%s", System.currentTimeMillis() - ss));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }


    private static void noThread() {
        long ss = System.currentTimeMillis();
        AtomicInteger count= new AtomicInteger();
        for (int i = 0; i < 100; i++) {


                try {
                    new Thread().sleep(1000L);
                    System.out.println(String.format("time:%s", System.currentTimeMillis() - ss));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}
