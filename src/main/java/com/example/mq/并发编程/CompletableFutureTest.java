package com.example.mq.并发编程;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author 钟金灿
 * @since 2022/3/10
 */
public class CompletableFutureTest {
    @Test
    public void go() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> s = CompletableFuture.supplyAsync(() -> 1, Executors.newCachedThreadPool());
        CompletableFuture q = CompletableFuture.runAsync(() -> {
            System.out.println("okok");
            try {
                Thread.sleep(1212L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, Executors.newCachedThreadPool());


//        s.co

        System.out.println(s.get( ));
        q.get();
        System.out.println("end");
    }

    @Test
    public void go2() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(() -> {
            System.out.println("1");

            Thread thread1 = new Thread(() -> {
                System.out.println("2");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2222");
            });
            thread1.setDaemon(false);

            thread1.start();

            System.out.println("111111");
        });


        Thread.sleep(11500L);
    }

    private String extracted() {
        Thread thread = new Thread();
        thread.setDaemon(false);
        thread.start();
        return "sdsds";
    }



    /**
     * 可以尝试 分别运行 testNormalThread(); 和 testCompletableFuture(); 来感受区别
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("我是Main线程，我结束后，不影响线程的运行：" + Thread.currentThread().getName());
//        testNormalThread();
        testCompletableFuture();

    }

    /**
     * 自己使用 Thread 方式模拟 守护线程和非守护线程
     */
    public static void testNormalThread() {
        Thread thread = new Thread(() -> {
            System.out.println("我是子线程,Main线程结束后，我依然运行：" + Thread.currentThread().getName());

            try {
                Thread.sleep(1000L);
                System.out.println("======执行操作 A====");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 将线程设置为守护线程
//        thread.setDaemon(true);
        thread.start();
    }

    /**
     * CompletableFuture 使用 voidCompletableFuture.get() 和不使用 voidCompletableFuture.get() 的区别
     */
    public static void testCompletableFuture() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("我是子线程,Main线程结束后，我依然运行：" + Thread.currentThread().getName());

            try {
                Thread.sleep(1000L);
                System.out.println("======执行操作 A====");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenCompleteAsync((r, e) -> {
            System.out.println("======执行操作 B====");
        });
        try {
            // 该方法会阻塞Main线程，告诉Main线程，我执行完了，你才可以结束
            voidCompletableFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


