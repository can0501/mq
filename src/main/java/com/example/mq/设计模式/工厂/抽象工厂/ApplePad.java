package com.example.mq.设计模式.工厂.抽象工厂;

public class ApplePad implements IPad {

    @Override
    public void generate(String type) {
        System.out.println(String.format("ApplePad[%s]", type));
    }
}
