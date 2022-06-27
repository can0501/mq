package com.example.mq.设计模式.单例;

/**
 * @author 钟金灿
 * @since 2021/9/12
 */
public class 懒汉dobleCheck {
    //私有化构造方法
    private 懒汉dobleCheck() {
    }

    //提供静态实例
    //volatile避免指令重排
    private static volatile 懒汉dobleCheck instance;

    //提供获取实例方法
    public static 懒汉dobleCheck getInstance() {
        if (instance == null) {
            synchronized (懒汉dobleCheck.class) {
                if (instance == null) {
                    instance = new 懒汉dobleCheck();
                }
            }
        }
        return instance;
    }
}
