package com.example.mq.设计模式.工厂.抽象工厂;

public class HuaWeiFactory implements IFactory {
    @Override
    public IPhone createPhoneFactory() {
        return new HuaWeiPhone();
    }

    @Override
    public IPad createPadFactory() {
        return new HuaWeiPad();
    }

}
