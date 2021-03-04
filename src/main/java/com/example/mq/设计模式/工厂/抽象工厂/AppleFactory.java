package com.example.mq.设计模式.工厂.抽象工厂;

public class AppleFactory implements IFactory {
    @Override
    public IPhone createPhoneFactory() {
        return new ApplePhone();
    }

    @Override
    public IPad createPadFactory() {
        return new ApplePad();
    }
}
