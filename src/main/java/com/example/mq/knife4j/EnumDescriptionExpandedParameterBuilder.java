package com.example.mq.knife4j;


import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ExpandedParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterExpansionContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.transform;
import static springfox.documentation.schema.Collections.collectionElementType;
import static springfox.documentation.schema.Collections.isContainerType;

/**
 * @author 钟金灿
 * @since 2022/3/31
 */
@Component
public class EnumDescriptionExpandedParameterBuilder implements ExpandedParameterBuilderPlugin {

    private final TypeResolver resolver;

    public EnumDescriptionExpandedParameterBuilder(
            TypeResolver typeResolver) {
        this.resolver = typeResolver;

    }


    @Override
    public void apply(ParameterExpansionContext context) {

        java.util.Optional<ApiModelProperty> apiModelPropertyOptional = java.util.Optional.ofNullable(context.findAnnotation(ApiModelProperty.class).orNull());

        Class<?> erasedType = context.getFieldType().getErasedType();

        ResolvedType resolved = this.resolver.resolve(context.getFieldType());

        if (isContainerType(resolved)) {
            resolved = Optional.ofNullable(resolved = context.getFieldType()).orElse(resolved);
            ResolvedType itemType = collectionElementType(resolved);
            if (IDescEnum.class.isAssignableFrom(itemType.getErasedType())) {
                ModelReference itemModel = new ModelRef(int.class.getSimpleName(), new AllowableListValues(Arrays.stream(itemType.getErasedType().getEnumConstants()).map(f -> {
                    IDescEnum f1 = (IDescEnum) f;
                    return f1.getValue().toString();
                }).collect(Collectors.toList()), "LIST"));
                context.getParameterBuilder()
                        .description(apiModelPropertyOptional.map(ApiModelProperty::value).orElse("") + ":" + enumValues(itemType.getErasedType()))
                        .modelRef(new ModelRef(int.class.getSimpleName(), itemModel));
            }
        }
        if (IDescEnum.class.isAssignableFrom(erasedType)) {
            context.getParameterBuilder()
                    .description(apiModelPropertyOptional.map(ApiModelProperty::value).orElse("") + ":" + enumValues(erasedType))
                    .allowableValues(allowableListValues(erasedType))
                    .modelRef(new ModelRef(int.class.getSimpleName()));
        }
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
}
