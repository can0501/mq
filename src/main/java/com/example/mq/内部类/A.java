package com.example.mq.内部类;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class A {
    private int a;

    public class Aa  {
        private int aa = a;
    }

    public Object createAa(){
        return new Aa();
    }

    public void add(){
        a++;
    }

    public static void main(String[] args) {
//        A a = new A();
//        Object aa = a.createAa();
//        a.add();
//        a.add();
//        a.add();
        ArrayList l = new ArrayList();
//        l.pop
//        Iterator iterator = l.iterator();
//
//        l.add(1);
//        l.add(1);
//        l.add(1);
//        l.add(1);
//        l.add(1);
//        l.add(1);
//        iterator.next();

        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

//        linkedList.push();
        System.out.println(linkedList.pop());




        HashMap hashMap = new HashMap();
//        a.add();
//        System.out.println(JSONObject.toJSONString(aa));

    }


}
