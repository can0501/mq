package com.example.mq.并发编程;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

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

}
