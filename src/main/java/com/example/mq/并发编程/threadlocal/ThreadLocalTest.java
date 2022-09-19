package com.example.mq.并发编程.threadlocal;

import com.example.mq.反射.C;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class ThreadLocalTest {
    @Test
    public void a() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            CompletableFuture.runAsync(() -> {
                System.out.println(1);
            }, executorService);
        }

    }

    @Test
    public void b() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
//                System.out.println(1);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "ss";
            }, executorService);
            list.add(stringCompletableFuture);
        }

        for (CompletableFuture<String> stringCompletableFuture : list) {
            System.out.println(stringCompletableFuture.getNow("2"));
        }

    }

    @Test
    public void c() throws ExecutionException, InterruptedException {
        InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {

                try {
                    threadLocal.set(1);

                    Thread thread = new Thread(() -> {
                        System.out.println("before" + threadLocal.get());
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("after" + threadLocal.get());
                    });
                    thread.start();
                    threadLocal.set(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "ss";
            }, executorService);
            list.add(stringCompletableFuture);
        }

        for (CompletableFuture<String> stringCompletableFuture : list) {
//            System.out.println();
            stringCompletableFuture.get();
        }


    }

    @Test
    public void cd() throws ExecutionException, InterruptedException {
//        InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
//
//        ExecutorService executorService = Executors.newCachedThreadPool();
//
//        List<CompletableFuture<String>> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
//
//                try {
//                    threadLocal.set(1);
//
//                    Thread thread = new Thread(() -> {
//                        System.out.println("before" + threadLocal.get());
//                        try {
//                            Thread.sleep(1000L);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println("after" + threadLocal.get());
//                    });
//                    thread.start();
//                    threadLocal.set(2);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return "ss";
//            }, executorService);
//            list.add(stringCompletableFuture);
//        }
//
//        for (CompletableFuture<String> stringCompletableFuture : list) {
////            System.out.println();
//            stringCompletableFuture.get();
//        }

        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
        threadLocal1.set(1);
        System.gc();

        ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();
        threadLocal2.set(2);
        threadLocal2.get();
        threadLocal2.remove();


        //System.out.println(threadLocal1.get());
        //System.gc();
        //threadLocal1.get();
        //System.out.println(threadLocal1.get());
        ////threadLocal1 = null;
        //System.gc();
        //


        //ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.execute(()->{
        //
        //});
        //threadLocal1.set(1);
        //threadLocal1.get();
        //threadLocal1.get();
        //
        //
        //
        //threadLocal2.set(2);
        //
        //
        //System.out.println(threadLocal1.get());

        InheritableThreadLocal<Integer> integerInheritableThreadLocal = new InheritableThreadLocal<>();
        //Thread


    }


    @Test
    public void aaa() throws InterruptedException {

        Executor executor = Executors.newCachedThreadPool();
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        System.out.println(Thread.currentThread().getName() + "\t 入队列 1");
        synchronousQueue.offer("1");
        System.out.println(Thread.currentThread().getName() + "\t 入队列 2");
        System.out.println(synchronousQueue.offer("2"));
        System.out.println(Thread.currentThread().getName() + "\t 入队列 3");
        synchronousQueue.offer("3");

        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(6);
        arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(1);
        System.out.println(1);
    }

    @Test
    public void arrayBlockingQueue() throws InterruptedException {


        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(1);
        System.out.println(1);
    }

    @Test
    public void thread() throws InterruptedException {

        final Thread thread = new Thread(() -> {
            System.out.println(1);
            try {

                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("InterruptedException");
                //e.printStackTrace();
                final boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println(interrupted);
            }
        });

        thread.start();

        Thread.sleep(500L);

        System.out.println(thread.getState());

        thread.interrupt();

        thread.join();

        System.out.println(thread.getState());


        System.out.println("ffffffff");

    }


    // 非抛异常 的中断
    @Test
    public void Interrupt_NoException() throws InterruptedException {

        final Thread thread = new Thread(() -> {
            System.out.println("thread.....");
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        });
        thread.start();
        Thread.sleep(500L);
        System.out.println(thread.getState());
        thread.interrupt();
        thread.join();
        System.out.println(thread.getState());
        System.out.println("end");

        //thread.....
        //RUNNABLE
        //        TERMINATED
        //end
    }


    // 抛异常 的中断
    @Test
    public void Interrupt_Exception() throws InterruptedException {

        final Thread thread = new Thread(() -> {
            System.out.println("thread.....");
            try {
                while (true) {
                    Thread.sleep(10000L);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("thread InterruptedException");
            }
        });
        thread.start();
        Thread.sleep(500L);
        System.out.println(thread.getState());
        thread.interrupt();
        thread.join();
        System.out.println(thread.getState());
        System.out.println("end");

        //thread.....
        //TIMED_WAITING
        //thread InterruptedException
        //TERMINATED
        //end
    }


}
