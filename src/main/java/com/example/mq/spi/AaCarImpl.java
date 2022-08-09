package com.example.mq.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author 钟金灿
 * @since 2022/2/22
 */
public class AaCarImpl implements Car {

    @Override
    public void getPrice() {
        System.out.println("Aa");
    }

    public static void main(String[] args) {
        ServiceLoader<Car> serviceLoader = ServiceLoader.load(Car.class);
        Iterator<Car> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            car.getPrice();
        }
    }
}
