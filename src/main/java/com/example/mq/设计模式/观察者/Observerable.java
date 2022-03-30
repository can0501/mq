package com.example.mq.设计模式.观察者;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 钟金灿
 * @since 2021/6/20
 */
public class Observerable {

    private List<Observer> observers = new ArrayList<>();

    public void setObservers(Observer observer) {
        observers.add(observer);
    }

    public void update() {
        System.out.println("被观察者doAction");
        this.notifyObserver();
    }

    public void notifyObserver() {
        observers.forEach(e -> e.update("被观察者"));
    }

    public static void main(String[] args) {
        Observer oa = new Observer_A();
        Observer ob = new Observer_B();
        Observer oc = new Observer_C();


        Observerable observerable = new Observerable();
        observerable.setObservers(oa);
        observerable.setObservers(ob);
        observerable.setObservers(oc);

        observerable.update();

        System.out.println("-------------------------------");
        observerable.notifyObserver();

    }
}
