package com.example.mq.并发编程.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 钟金灿
 * @since 2021/6/27
 */
public class Lock {
    @Test
    public void Rlock() {
        ReentrantLock rLock = new ReentrantLock();
    }

    @Test
    public void Clock() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 10; i++) {

            executorService.execute(() -> {
                try {
                    Thread.sleep(4000L);
                    System.out.println("A");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(2L, TimeUnit.SECONDS);

        System.out.println(1);

    }

    @Test
    public void Slock() {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 10; i++) {

            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("A");
                    while (true) {

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }

    }

    @Test
    public void XWlock() throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(() -> {
            java.util.concurrent.locks.Lock wLock = readWriteLock.writeLock();
            wLock.lock();
            System.out.println(3);
        });

        executorService.execute(() -> {
            java.util.concurrent.locks.Lock xLock = readWriteLock.readLock();
            xLock.lock();
            System.out.println(1);
        });

        executorService.execute(() -> {
            java.util.concurrent.locks.Lock xLock = readWriteLock.readLock();
            xLock.lock();
            System.out.println(2);
        });


        Thread.sleep(111L);


    }

    @Test
    public void sadlock() throws InterruptedException {
        Object o = new Object();
        Thread thread1 = new Thread(() -> {
            LockSupport.park();
            System.out.println(1);
        });
        Thread thread2 = new Thread(() -> {
            LockSupport.unpark(thread1);
            System.out.println(2);
        });
        for (int i = 0; i < 30; i++) {

        }


        thread1.start();
        Thread.sleep(111L);
        thread2.start();


        Thread.sleep(111L);

    }


}
