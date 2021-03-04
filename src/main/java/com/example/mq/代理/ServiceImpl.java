package com.example.mq.代理;

public class ServiceImpl implements Service{
    @Override
    public String doThing(String s) {
        System.out.println(s);
        return s;
    }
}
