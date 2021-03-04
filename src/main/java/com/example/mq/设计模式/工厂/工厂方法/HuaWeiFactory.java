package com.example.mq.设计模式.工厂.工厂方法;

public class HuaWeiFactory implements PhoneFactory{
    @Override
    public IPhone createPhoneFactory() {
        return new HuaWeiPhone();
    }
}
