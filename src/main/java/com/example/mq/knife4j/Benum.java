package com.example.mq.knife4j;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * @author 钟金灿
 * @since 2022/3/30
 */
@JsonSerialize(using = IDescEnumSerializer.class)
@JsonDeserialize(using = IDescEnumDeserializer.class)
public enum Benum implements IDescEnum {
    枚举B1(1, "枚举B1"),

    枚举B2(2, "枚举B2"),
    ;
    @Getter
    private Integer value;
    @Getter
    private String desc;

    Benum(int i, String 枚举1) {
        this.value = i;
        this.desc = 枚举1;
    }
}
