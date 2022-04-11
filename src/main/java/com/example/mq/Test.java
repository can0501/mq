package com.example.mq;

import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 钟金灿
 * @since 2021/7/3
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);
   getIntegers(list);
        System.out.println(list);
    }

    private static void getIntegers(List<Integer> list) {
        list = list.stream().sorted(Integer::compareTo).collect(Collectors.toList());

    }
    @Data
    public static class Qaq{
        public   Integer asd;

        public   Integer bb;

    }

    @org.junit.Test
    public void test1(){
        List<Qaq> list = new ArrayList<>();
        Qaq q = new Qaq();
        q.setAsd(1);
        list.add(q);
        list.stream().collect(Collectors.toMap(Qaq::getAsd, Qaq::getBb));

     }


    @org.junit.Test
    public void test23(){
        Integer a = 1;
        add1(a);
        System.out.println(a);

    }

    public Integer add1(Integer a) {
        a = a + 1;
        return a;
    }

    @org.junit.Test
    public void test233(){
        System.out.println(new BigDecimal("0.00").intValue());

    }

    @org.junit.Test
    public void test2333(){
        Lists.newArrayListWithExpectedSize(12);

    }
}
