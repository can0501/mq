package com.example.mq.juc.sleep和wait;

/**
 * @author 钟金灿
 * @since 2021/6/21
 */
public class 死锁 {


    static class LockA {

    }
    static class LockB {

    }
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (LockA.class) {
                System.out.println("线程1锁住A");
                try {
                    Thread.sleep(5000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                synchronized (LockB.class) {
                    System.out.println("线程1锁住B");

                }

            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (LockB.class) {
                System.out.println("线程2锁住B");
                try {
                    Thread.sleep(5000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                synchronized (LockA.class) {
                    System.out.println("线程2锁住A");

                }

            }
        });

        thread.start();
        thread2.start();
    }
}
