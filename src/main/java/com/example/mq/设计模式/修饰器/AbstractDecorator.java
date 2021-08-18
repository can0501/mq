package com.example.mq.设计模式.修饰器;

/**
 * @author 钟金灿
 * @since 2021/8/18
 */
public class AbstractDecorator implements Eat {

    public Eat eat;

    public void setEat(Eat eat) {
        this.eat = eat;
    }

    @Override
    public String eat() {
        return this.eat.eat();
    }



}
