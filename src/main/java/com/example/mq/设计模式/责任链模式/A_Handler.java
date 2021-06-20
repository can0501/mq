package com.example.mq.设计模式.责任链模式;

/**
 * @author 钟金灿
 * @since 2021/6/20
 */
public class A_Handler extends Handler {

    @Override
    public Boolean doAction(String a) {
            System.out.println("A do action");
        if (a.equals("abc")) {
            return this.next.doAction(a);
        }
        return false;
    }
}
