package com.example.mq.knife4j;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * @author 钟金灿
 * @since 2022/3/30
 */

public enum AEnum implements IDescEnum {
    枚举1(1, "枚举1"),

    枚举2(2, "枚举2"),
    ;
    @Getter
    private Integer value;
    @Getter
    private String desc;

    AEnum(int i, String 枚举1) {
        this.value = i;
        this.desc = 枚举1;
    }
}
