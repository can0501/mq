
package com.example.mq.knife4j;


 import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.schema.Annotations;
import springfox.documentation.schema.ModelProperty;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterExpansionContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.transform;
import static springfox.documentation.schema.Collections.collectionElementType;
import static springfox.documentation.schema.Collections.isContainerType;
import static springfox.documentation.swagger.common.SwaggerPluginSupport.pluginDoesApply;


@Configuration
@Slf4j
public class EnumsConfig {

    /**
     * 包装swagger集合类型的枚举
     */
    @Bean
    public ModelBuilderPlugin enumApiModelBuilderPlugin() {
        return new ModelBuilderPlugin() {

            @Override
            public void apply(ModelContext context) {
                Map<String, ModelProperty> properties = context.getBuilder().build().getProperties();
                properties.forEach((a, b) -> {
                    if (isContainerType(b.getType())) {
                        ResolvedType itemType = collectionElementType(b.getType());
                        if (IDescEnum.class.isAssignableFrom(itemType.getErasedType())) {
                            b.updateModelRef((qq) -> {
                                ModelReference itemModel = new ModelRef(int.class.getSimpleName(), new AllowableListValues(Arrays.stream(itemType.getErasedType().getEnumConstants()).map(f -> {
                                    IDescEnum f1 = (IDescEnum) f;
                                    return f1.getValue().toString();
                                }).collect(Collectors.toList()), "LIST"));
                                return new ModelRef(int.class.getSimpleName(), itemModel);
                            });
                        }
                    }
                });
            }

            @Override
            public boolean supports(DocumentationType delimiter) {
                return pluginDoesApply(delimiter);
            }
        };
    }

    /**
     * 包装swagger get请求枚举
     */
    @Bean
    ExpandedParameterBuilderPlugin enumExpandedParameterBuilderPlugin(TypeResolver resolver) {
        return new ExpandedParameterBuilderPlugin() {


            @Override
            public void apply(ParameterExpansionContext context) {

                Optional<ApiModelProperty> apiModelPropertyOptional = Optional.ofNullable(context.findAnnotation(ApiModelProperty.class).orNull());

                Class<?> erasedType = context.getFieldType().getErasedType();

                ResolvedType resolved = resolver.resolve(context.getFieldType());

                String name = context.getParameterBuilder().build().getName();

                if (isContainerType(resolved)) {
                    resolved = Optional.ofNullable(resolved = context.getFieldType()).orElse(resolved);
                    ResolvedType itemType = collectionElementType(resolved);
                    if (IDescEnum.class.isAssignableFrom(itemType.getErasedType())) {
                        ModelReference itemModel = new ModelRef(int.class.getSimpleName(), new AllowableListValues(Arrays.stream(itemType.getErasedType().getEnumConstants()).map(f -> {
                            IDescEnum f1 = (IDescEnum) f;
                            return f1.getValue().toString();
                        }).collect(Collectors.toList()), "LIST"));
                        context.getParameterBuilder()
                                .description(getDescription(apiModelPropertyOptional, itemType.getErasedType(), name))
                                .modelRef(new ModelRef(int.class.getSimpleName(), itemModel));
                    }
                }
                if (IDescEnum.class.isAssignableFrom(erasedType)) {
                    context.getParameterBuilder()
                            .description(getDescription(apiModelPropertyOptional, erasedType, name))
                            .allowableValues(allowableListValues(erasedType))
                            .modelRef(new ModelRef(int.class.getSimpleName()));
                }
            }

            private String getDescription(Optional<ApiModelProperty> apiModelPropertyOptional, Class<?> erasedType, String name) {
                return apiModelPropertyOptional.map(ApiModelProperty::value).orElse(name) + ":" + enumValues(erasedType);
            }

            @Override
            public boolean supports(DocumentationType delimiter) {
                return SwaggerPluginSupport.pluginDoesApply(delimiter);
            }


            private List<String> enumValues(final Class<?> subject) {
                IDescEnum[] enumConstants = (IDescEnum[]) subject.getEnumConstants();
                return Arrays.stream(enumConstants).map(f -> f.getValue() + ":" + f.getDesc()).collect(Collectors.toList());
            }

            private AllowableListValues allowableListValues(final Class<?> subject) {
                if (IDescEnum.class.isAssignableFrom(subject)) {
                    IDescEnum[] enumConstants = (IDescEnum[]) subject.getEnumConstants();
                    return new AllowableListValues(Arrays.stream(enumConstants).map(f -> f.getValue().toString()).collect(Collectors.toList()), int.class.getSimpleName());
                }
                return new AllowableListValues(transform(Lists.newArrayList(subject.getEnumConstants()), (Function<Object, String>) Object::toString), String.class.getSimpleName());
            }
        };
    }

    /**
     * 包装swagger post请求枚举
     */
    @Bean
    ModelPropertyBuilderPlugin enumModelPropertyBuilderPlugin() {
        return new ModelPropertyBuilderPlugin() {
            @Override
            public void apply(ModelPropertyContext context) {
                com.google.common.base.Optional<ApiModelProperty> annotation = com.google.common.base.Optional.absent();
                String name = context.getBuilder().build().getName();

                if (context.getAnnotatedElement().isPresent()) {
                    annotation = annotation.or(ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
                }
                if (context.getBeanPropertyDefinition().isPresent()) {
                    annotation = annotation.or(Annotations.findPropertyAnnotation(
                            context.getBeanPropertyDefinition().get(),
                            ApiModelProperty.class));
                }

                final Class<?> rawPrimaryType = context.getBeanPropertyDefinition().get().getRawPrimaryType();
                ResolvedType resolve = context.getResolver().resolve(rawPrimaryType);

                if (isContainerType(resolve)) {
                    ModelPropertyBuilder builder = context.getBuilder();
                    ResolvedType itemType = collectionElementType(builder.build().getType());
                    if (IDescEnum.class.isAssignableFrom(itemType.getErasedType())) {
                        Optional<ApiModelProperty> optionalApiModelProperty = Optional.ofNullable(annotation.orNull());
                        IDescEnum[] values = (IDescEnum[]) itemType.getErasedType().getEnumConstants();
                        final List<String> displayValueList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue() + ":" + codedEnum.getDesc()).collect(Collectors.toList());
                        final List<String> valuesList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue().toString()).collect(Collectors.toList());
                        final AllowableListValues allowableListValues = new AllowableListValues(valuesList, int.class.getSimpleName());
                        context.getBuilder().description(getDesc(name, optionalApiModelProperty) + ":" + displayValueList)
                                .allowableValues(allowableListValues);
                    }
                }
                if (IDescEnum.class.isAssignableFrom(rawPrimaryType)) {
                    Optional<ApiModelProperty> optionalApiModelProperty = Optional.ofNullable(annotation.orNull());
                    IDescEnum[] values = (IDescEnum[]) rawPrimaryType.getEnumConstants();
                    final List<String> displayValueList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue() + ":" + codedEnum.getDesc()).collect(Collectors.toList());
                    final List<String> valuesList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue().toString()).collect(Collectors.toList());
                    final AllowableListValues allowableListValues = new AllowableListValues(valuesList, rawPrimaryType.getTypeName());
                    final ResolvedType resolvedType = context.getResolver().resolve(int.class);
                    context.getBuilder().description(getDesc(name, optionalApiModelProperty) + ":" + displayValueList)
                            .type(resolvedType).allowableValues(allowableListValues);
                }
            }

            private String getDesc(String name, Optional<ApiModelProperty> optionalApiModelProperty) {
                return optionalApiModelProperty.map(ApiModelProperty::value).orElse(name);
            }

            @Override
            public boolean supports(DocumentationType documentationType) {
                return true;
            }
        };
    }
}
