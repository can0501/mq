package com.example.mq.knife4j;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 钟金灿
 * @since 2022/3/30
 */
@JsonSerialize(using = IDescEnumSerializer.class)
@JsonDeserialize(using = IDescEnumDeserializer.class)
public interface IDescEnum {
    Integer getValue();

    String getDesc();






    public static void main(String[] args) {
        A a = new A();
        a.setIntttt(1);

        A b = new A();
        b.setIntttt(1);

        List<A> sd = Lists.newArrayList(a, b);
        System.out.println(sd.stream().map(e->e.getIntttt()).distinct());
    }
}
