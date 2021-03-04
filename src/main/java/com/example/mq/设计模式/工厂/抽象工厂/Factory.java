package com.example.mq.设计模式.工厂.抽象工厂;

public class Factory {
    IFactory createPhone(String phone) {
        IFactory phoneFactory;
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
        IFactory apple = factory.createPhone("apple");
        IFactory huawei = (IFactory) factory.createPhone("HuaWei");

        apple.createPhoneFactory().generate("IPhone12");
        huawei.createPhoneFactory().generate("Mate40");

        apple.createPadFactory().generate("IPad Pro");
        huawei.createPadFactory().generate("Mate Pad");

    }



}
