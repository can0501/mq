package com.example.mq.设计模式.责任链模式;

/**
 * @author 钟金灿
 * @since 2021/6/20
 */
public abstract class Handler {
    protected Handler next;

    public void setHandler(Handler handler) {
        this.next = handler;
    }

    public abstract Boolean doAction(String a);

    public static void main(String[] args) {
        Handler handler1 = new A_Handler();
        Handler handler2 = new B_Handler();
        Handler handler3 = new C_Handler();

        handler1.setHandler(handler2);
        handler2.setHandler(handler3);

        System.out.println(handler1.doAction("abc"));
    }


}
