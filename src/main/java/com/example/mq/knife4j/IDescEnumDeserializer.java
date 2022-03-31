package com.example.mq.knife4j;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * value或者desc转枚举 反序列化
 * @author 钟金灿
 * @since 2022/3/28
 */
public class IDescEnumDeserializer extends JsonDeserializer<IDescEnum> {

    @SneakyThrows
    @Override
    public IDescEnum deserialize(JsonParser jsonParser, DeserializationContext ctxt) {
        final String param = jsonParser.getText();
        final JsonStreamContext parsingContext = jsonParser.getParsingContext();
        final String currentName = parsingContext.getCurrentName();
        final Object currentValue = parsingContext.getCurrentValue();
        final Field declaredField = currentValue.getClass().getDeclaredField(currentName);
        final Class<IDescEnum> targetType = (Class<IDescEnum>) declaredField.getType();
        return Arrays.stream(targetType.getEnumConstants())
                .filter(x -> x.getValue().toString().equals(param) || x.getDesc().equals(param))
                .findFirst()
                .orElse(null);
    }
}
