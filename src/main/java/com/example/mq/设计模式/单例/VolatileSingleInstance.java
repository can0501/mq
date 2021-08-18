package com.example.mq.设计模式.单例;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileSingleInstance {
    private static volatile SingleInstance instance;


    //double check
    public static SingleInstance getInstance() {

        if (instance == null) {
            synchronized (VolatileSingleInstance.class) {
                if (instance == null) {
                    instance = new SingleInstance();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingleInstance instance = VolatileSingleInstance.getInstance();
        System.out.println(instance.hashCode());
        instance = VolatileSingleInstance.getInstance();
        System.out.println(instance.hashCode());

        ExecutorService executor = Executors.newCachedThreadPool();


        for (int i = 0; i < 1111; i++) {
            executor.execute(() -> {
                System.out.println(VolatileSingleInstance.getInstance().hashCode());

            });
        }


    }
}
