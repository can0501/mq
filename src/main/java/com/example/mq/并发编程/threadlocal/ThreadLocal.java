package com.example.mq.并发编程.threadlocal;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadLocal {
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
    public void b() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<CompletableFuture<String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(1);
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "ss";
            }, executorService);
            list.add(stringCompletableFuture);
        }

        for (CompletableFuture<String> stringCompletableFuture : list) {
            System.out.println(stringCompletableFuture.getNow("1"));
        }

        }




}
