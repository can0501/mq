package com.example.mq.设计模式.工厂.抽象工厂;

public class ApplePhone implements IPhone {

    @Override
    public void generate(String type) {
        System.out.println(String.format("ApplePhone[%s]", type));
    }
}
