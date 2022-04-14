package com.example;

import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 钟金灿
 * @since 2021/7/3
 */
public class Test3 {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("data总数：" + list.size());
        System.out.println("********************1********************");
        int count = 2;
        for (int i = 0; i < count; i++) {
            for (Integer data : list) {
                if (data % count == i){
                    System.out.println("执行片：" + i + "=>执行data：" + data);
                }
            }
            System.out.println("========当前片执行完===========：" + i);
        }

        System.out.println("********************1********************");
        System.out.println("范围区间极值：" + list.get(0) + "==" + list.get(list.size() - 1));
        System.out.println("********************2********************");

        // 总数
        int countSize = list.size();
        // 商
        int number = countSize / count;
        for (int i = 0; i < count; i++) {
            List<Integer> integers;
            if (i == count - 1) {
                // 0,2  3,4
                integers = list.subList(i * number, countSize);
                // System.out.println("index:" + i * number + "," +countSize );
            } else {
                integers = list.subList(i * number, (i + 1) * number);
                // System.out.println("index:" + i * number + "," +(i + 1) * number );
            }
            System.out.println("========当前片执行完===========：" + i + "处理数据为：" + JSONObject.toJSONString(integers));
        }
        System.out.println("********************2********************");


        System.out.println("********************3********************");

        // 差值
        // int offer = list.get(list.size() - 1) - list.get(0);
        // 每片数量
        int rows = countSize / count;;
        // 上一片最后id
        Map<String, Integer> shardingData = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Integer lastIndex = null;
            for (Integer data : list) {
                List<Integer> itemList = Lists.newArrayList();
                // 起始id
                int startIndex = Objects.isNull(shardingData.get(i + "")) ? i :shardingData.get(i + "");
                // 最大id
                lastIndex = startIndex + rows;
                if (i+1 == count){
                    // 最后一片
                    lastIndex = startIndex + countSize;
                }
                if (data <= lastIndex && data > startIndex){
                    itemList.add(data);
                    System.out.println("3==》执行data:" + itemList + "==筛选条件为：" + "data > " +  startIndex + " and data <= " +  (startIndex + rows));
                }
            }
            shardingData.put(i+1 + "",lastIndex);
            System.out.println("========当前片执行完===========：" + i);
        }
        System.out.println("********************3********************");
    }}
