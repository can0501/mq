package com.example.mq.设计模式.工厂.工厂方法;

public class Factory {
    PhoneFactory createPhone(String phone) {
        PhoneFactory phoneFactory;
        switch (phone) {
            case "apple":
                phoneFactory = new AppleFactory();
                break;
            case "HuaWei":
                phoneFactory = new HuaWeiFactory();
                break;
            default:
                phoneFactory = null;
        }
        return phoneFactory;
    }


    public static void main(String[] args) {
        Factory factory = new Factory();
        PhoneFactory apple = factory.createPhone("apple");
        PhoneFactory huawei = (PhoneFactory) factory.createPhone("HuaWei");

        apple.createPhoneFactory().generate("IPhone12");
        huawei.createPhoneFactory().generate("Mate40");

    }



}
