package com.example.mq.反射;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.springframework.beans.PropertyAccessorUtils;
import sun.reflect.misc.FieldUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author 钟金灿
 * @since 2022/3/23
 */
public class InvokeTest {
    @Test
    public void a() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        A a = new A();
        a.setA1(1);

        Field[] declaredFields = FieldUtil.getDeclaredFields(A.class);
        Stream.of(declaredFields).forEach(System.out::println);
//        private java.lang.Integer com.example.mq.反射.A.a1
//        private java.lang.Integer com.example.mq.反射.A.a2
//        private java.lang.Integer com.example.mq.反射.A.a3
//        private java.lang.Integer com.example.mq.反射.A.a4
//        private java.lang.Integer com.example.mq.反射.A.a5

//        System.out.println(PropertyUtils.getProperty(a, "a1"));



//        B b = new B();
//        b.setA1(1);
//
//        Class<? extends B> aClass = b.getClass();
//        System.out.println(aClass.getInterfaces().getName());

        C c = new C();
        Arrays.stream(c.getClass().getInterfaces()).forEach(e -> {
            System.out.println(e.getTypeName());
        });

    }
}
