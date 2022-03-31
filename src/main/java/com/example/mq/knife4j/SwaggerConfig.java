package com.example.mq.knife4j;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 钟金灿
 * @since 2022/3/30
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .contact("xx@qq.com")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.mq.knife4j"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


//    @Bean
    public ModelPropertyBuilderPlugin getIdescEnumModelPropertyBuilderPlugin() {
        return new ModelPropertyBuilderPlugin() {

            @Override
            public void apply(ModelPropertyContext context) {
                com.google.common.base.Optional<BeanPropertyDefinition> optional = context.getBeanPropertyDefinition();
                if (!optional.isPresent()) {
                    return;
                }
                if (Objects.isNull(optional.get().getField())) {
                    return;
                }
                try {
                    final Class<?> fieldType = optional.get().getField().getRawType();
                    addDescForEnum(context, fieldType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean supports(DocumentationType delimiter) {
                return true;
            }

            private void  addDescForEnum(ModelPropertyContext context, Class<?> fieldType) {
                if (!Objects.isNull(fieldType) && IDescEnum.class.isAssignableFrom(fieldType)) {
                    IDescEnum[] enumConstants = (IDescEnum[]) fieldType.getEnumConstants();
                    List<String> displayValues = Arrays.stream(enumConstants).map(item -> item.getValue().toString() + ":" + item.getDesc()).collect(Collectors.toList());
                    ModelPropertyBuilder builder = context.getBuilder();
                    Field descField = ReflectionUtils.findField(builder.getClass(), "description");
                    ReflectionUtils.makeAccessible(descField);
                    String joinText = ReflectionUtils.getField(descField, builder)
                            + ":" + String.join(";", displayValues);
                    builder.description(joinText).type(context.getResolver().resolve(Integer.class));

                    List<String> asd = Arrays.stream(enumConstants).map(item -> item.getValue().toString()).collect(Collectors.toList());

                    AllowableListValues allowableValues = new AllowableListValues(asd, "LIST");
                    builder.allowableValues(allowableValues);
                }
            }
        };
    }
}
