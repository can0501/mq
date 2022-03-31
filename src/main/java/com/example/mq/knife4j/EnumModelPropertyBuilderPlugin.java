package com.example.mq.knife4j;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.Annotations;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 钟金灿
 * @since 2022/3/31
 */
@Component
public class EnumModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public void apply(ModelPropertyContext context) {
        Optional<ApiModelProperty> annotation = Optional.absent();

        if (context.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(ApiModelProperties.findApiModePropertyAnnotation(context.getAnnotatedElement().get()));
        }
        if (context.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(Annotations.findPropertyAnnotation(
                    context.getBeanPropertyDefinition().get(),
                    ApiModelProperty.class));
        }

        final Class<?> rawPrimaryType = context.getBeanPropertyDefinition().get().getRawPrimaryType();
        //过滤得到目标类型
        if (annotation.isPresent() && IDescEnum.class.isAssignableFrom(rawPrimaryType)) {

            //获取CodedEnum的code值
            IDescEnum[] values = (IDescEnum[]) rawPrimaryType.getEnumConstants();
            final List<String> displayValueList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue() + ":" + codedEnum.getDesc()).collect(Collectors.toList());
            final List<String> valuesList = Arrays.stream(values).map(codedEnum -> codedEnum.getValue().toString()).collect(Collectors.toList());
            final AllowableListValues allowableListValues = new AllowableListValues(valuesList, rawPrimaryType.getTypeName());
            //固定设置为int类型
            java.util.Optional<String> example = java.util.Optional.ofNullable(annotation.get().example());
            final ResolvedType resolvedType = context.getResolver().resolve(int.class);
            context.getBuilder().description(annotation.get().value() + ":" + displayValueList)
                    .example((Object) example.orElse(valuesList.get(0)))
                    .type(resolvedType).allowableValues(allowableListValues);
        }
    }


    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

}
