package com.example.mq.knife4j;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import lombok.SneakyThrows;

import java.util.Arrays;

/**
 * value或者desc转枚举 反序列化
 *
 * @author 钟金灿
 * @since 2022/3/28
 */
public class IDescEnumDeserializer extends JsonDeserializer<IDescEnum> implements ContextualDeserializer {

    private Class<? extends IDescEnum> clazz;

    public IDescEnumDeserializer() {
    }

    public IDescEnumDeserializer(Class<? extends IDescEnum> clazz) {
        this.clazz = clazz;
    }

    @SneakyThrows
    @Override
    public IDescEnum deserialize(JsonParser jsonParser, DeserializationContext ctxt) {
        final String param = jsonParser.getText();
        return Arrays.stream(clazz.getEnumConstants())
                .filter(x -> x.getValue().toString().equals(param))
                .findFirst()
                .orElse(null);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JavaType type = property.getType();
        // 如果是容器，则返回容器内部枚举类型
        while (type.isContainerType()) {
            type = type.getContentType();
        }
        return new IDescEnumDeserializer((Class<? extends IDescEnum>) type.getRawClass());
    }
}
