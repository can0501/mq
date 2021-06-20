package com.example.mq.线程池;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.PrinterGraphics;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 钟金灿
 * @since 2021/5/26
 */
public class ThreadOO {


//
//    public void s() {
//        AopContext.currentProxy()
//    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Thread1 thread1 = new Thread1();

        Thread thread2 = new Thread(() -> {
            System.out.println("实现Runnable接口");
        });

        FutureTask futureTask = new FutureTask(new Callable1());
        Thread thread3 = new Thread(futureTask);
        Thread thread4 = new Thread(futureTask);

        thread1.start();


        thread2.start();

        thread3.start();


//        thread3.start();



        System.out.println(futureTask.get());
        thread4.start();
        new Thread().sleep(111);
        System.out.println(futureTask.get());




    }

    public static class Thread1 extends Thread {
        @Override
        public void run(){
            super.run();
            System.out.println("继承Tread实现线程");
        }
    }

    public static class Callable1 implements Callable<Integer> {

        int i = 1;

        @Override
        public Integer call() throws Exception {
            System.out.println("实现Callable带返回值");
            return i++;
        }
    }

}
