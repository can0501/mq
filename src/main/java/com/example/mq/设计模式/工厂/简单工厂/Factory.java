package com.example.mq.设计模式.工厂.简单工厂;

public class Factory {
    IPhone createPhone(String phone) {
        IPhone iPhone;
        switch (phone) {
            case "apple":
                iPhone = new ApplePhone();
                break;
            case "HuaWei":
                iPhone = new HuaWeiPhone();
                break;
            default:
                iPhone = null;
        }
        return iPhone;
    }


    public static void main(String[] args) {
        Factory factory = new Factory();
        IPhone apple = factory.createPhone("apple");
        HuaWeiPhone huawei = (HuaWeiPhone) factory.createPhone("HuaWei");

        apple.generate("IPhone12");


        huawei.generate("Mate40");

    }



}
