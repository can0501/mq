package com.example.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 钟金灿
 * @since 2021/7/3
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
             while (1 == 1) {


            }
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            while (1 == 1) {


            }

        });
        thread2.start();

    }
}
