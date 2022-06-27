package com.example.mq.设计模式.单例;

/**
 * @author 钟金灿
 * @since 2021/9/12
 */
public class 饿汉 {
    //私有化构造方法
    private 饿汉() {
    }

    //提供静态实例
    //直接初始化
    private static 饿汉 instance = new 饿汉();

    //提供获取实例方法
    public static 饿汉 getInstance() {
        return instance;
    }
}
