package com.example.mq.设计模式.修饰器;

/**
 * @author 钟金灿
 * @since 2021/8/18
 */
public class Main {

    public static void main(String[] args) {
        Eat eat = new EatImpl();
        System.out.println(eat.eat());

        AppleDecorator appleDecorator = new AppleDecorator();
        appleDecorator.setEat(eat);

        System.out.println(appleDecorator.eat());
//
        PearDecorator pearDecorator = new PearDecorator();
        pearDecorator.setEat(appleDecorator);
        System.out.println(pearDecorator.eat());



    }
}
