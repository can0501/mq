package com.example.mq.knife4j;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * WebConfig
 *
 * @author wink
 * @date 2021/10/18 11:22:51
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {
//        Integer转枚举 针对http-get方法,不过项目中全用post方法
        registry.addConverterFactory(new ConverterFactory<String, IDescEnum>() {
            @Override
            public <T extends IDescEnum> Converter<String, T> getConverter(Class<T> targetType) {
                return source -> Arrays.stream(targetType.getEnumConstants()).filter(e -> e.getValue().toString().equals(source)).findFirst().orElse(null);
            }
        });
    }
}
