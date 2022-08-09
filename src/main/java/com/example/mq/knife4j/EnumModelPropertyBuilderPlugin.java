package com.example.mq.knife4j;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.schema.Annotations;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static springfox.documentation.schema.Collections.collectionElementType;
import static springfox.documentation.schema.Collections.isContainerType;

/**
 * @author 钟金灿
 * @since 2022/3/31
 */
//@Component
public class EnumModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public void apply(ModelPropertyContext context) {
        Optional<ApiModelProperty> annotation = Optional.absent();
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
                java.util.Optional<ApiModelProperty> optionalApiModelProperty = java.util.Optional.ofNullable(annotation.orNull());
                IDescEnum[] values = (IDescEnum[]) itemType.getErasedType().getEnumConstants();
                final List<String> displayValueList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue() + ":" + codedEnum.getDesc()).collect(Collectors.toList());
                final List<String> valuesList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue().toString()).collect(Collectors.toList());
                final AllowableListValues allowableListValues = new AllowableListValues(valuesList, int.class.getSimpleName());
                context.getBuilder().description(getDesc(name, optionalApiModelProperty) + ":" + displayValueList)
                        .allowableValues(allowableListValues);
            }
        }
        if (IDescEnum.class.isAssignableFrom(rawPrimaryType)) {
            java.util.Optional<ApiModelProperty> optionalApiModelProperty = java.util.Optional.ofNullable(annotation.orNull());
            IDescEnum[] values = (IDescEnum[]) rawPrimaryType.getEnumConstants();
            final List<String> displayValueList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue() + ":" + codedEnum.getDesc()).collect(Collectors.toList());
            final List<String> valuesList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue().toString()).collect(Collectors.toList());
            final AllowableListValues allowableListValues = new AllowableListValues(valuesList, rawPrimaryType.getTypeName());
            final ResolvedType resolvedType = context.getResolver().resolve(int.class);
            context.getBuilder().description(getDesc(name, optionalApiModelProperty) + ":" + displayValueList)
                    .type(resolvedType).allowableValues(allowableListValues);
        }
    }

    private String getDesc(String name, java.util.Optional<ApiModelProperty> optionalApiModelProperty) {
        return optionalApiModelProperty.map(ApiModelProperty::value).orElse(name);
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

}
