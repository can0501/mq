package com.example.mq.设计模式.单例;

/**
 * @author 钟金灿
 * @since 2021/9/12
 */
public class 懒汉_静态内部类 {
    //私有化构造方法
    private 懒汉_静态内部类() {
    }

    //静态内部类提供实例final,statis
    static class InstanceHolder {
        private final static 懒汉_静态内部类 instance = new 懒汉_静态内部类();
    }

    //提供获取静态内部类实例方法
    public static 懒汉_静态内部类 getInstance() {
       return InstanceHolder.instance;
    }
}
