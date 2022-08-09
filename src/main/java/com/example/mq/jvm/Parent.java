package com.example.mq.jvm;

/**
 * @author 钟金灿
 * @since 2022/3/11
 */
public class Parent {

    private Object son;

    public Object getSon(){
        return new Son();
    }
    public Object setSon(Object son){
        this.son = son;
        return this.son;
    }

}
