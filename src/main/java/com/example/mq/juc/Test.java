package com.example.mq.juc;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {


        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
    }
}
