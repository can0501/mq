package com.example.mq.设计模式.单例;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IODHI {

    private  static class Holder {
        static SingleInstance instance = new SingleInstance();
    }

    public static SingleInstance getInstance() {
        return Holder.instance;
    }


    public static void main(String[] args) {
        SingleInstance instance = IODHI.getInstance();
        Holder holder = new Holder();
        System.out.println(instance.hashCode());
        instance = IODHI.getInstance();
        System.out.println(instance.hashCode());

        ExecutorService executor = Executors.newCachedThreadPool();


        for (int i = 0; i < 1111; i++) {
            executor.execute(() -> {
                System.out.println(IODHI.getInstance().hashCode());

            });
        }
    }
}
