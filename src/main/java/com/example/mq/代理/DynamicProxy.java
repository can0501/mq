package com.example.mq.代理;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    static class MyProxyHandler implements InvocationHandler {


        Object target;

        public void setObject(Object o) {
            this.target = o;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before");
            Object invoke = method.invoke(target, args);
            System.out.println("after");
            return invoke;
        }
    }

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        MyProxyHandler myProxyHandler = new MyProxyHandler();
        myProxyHandler.setObject(service);
        Service proxyTarget = (Service) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), myProxyHandler);
        String zhong = proxyTarget.doThing("zhong");
        System.out.println("res:" + zhong);

    }


}
