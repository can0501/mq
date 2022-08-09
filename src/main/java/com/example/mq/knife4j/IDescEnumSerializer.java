package com.example.mq.knife4j;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Objects;

/**
 * 枚举转desc 序列化
 * @author 钟金灿
 * @since 2022/3/28
 */
public class IDescEnumSerializer extends JsonSerializer<IDescEnum> {
    @Override
    public void serialize(IDescEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (Objects.isNull(value)) {
            gen.writeNull();
            return;
        }
        gen.writeString(value.getDesc());
    }
}
