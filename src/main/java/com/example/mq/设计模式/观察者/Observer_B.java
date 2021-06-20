package com.example.mq.设计模式.观察者;

/**
 * @author 钟金灿
 * @since 2021/6/20
 */
public class Observer_B implements Observer {
    @Override
    public void update(String string) {
        System.out.println("观察者B观察" + string);
    }
}
