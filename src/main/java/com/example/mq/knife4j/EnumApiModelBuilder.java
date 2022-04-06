

package com.example.mq.knife4j;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelProperty;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static springfox.documentation.schema.Collections.collectionElementType;
import static springfox.documentation.schema.Collections.isContainerType;
import static springfox.documentation.swagger.common.SwaggerPluginSupport.pluginDoesApply;

@Component
public class EnumApiModelBuilder implements ModelBuilderPlugin {
    private final TypeResolver typeResolver;
    private final TypeNameExtractor typeNameExtractor;

    @Autowired
    public EnumApiModelBuilder(
            TypeResolver typeResolver,
            TypeNameExtractor typeNameExtractor) {
        this.typeResolver = typeResolver;
        this.typeNameExtractor = typeNameExtractor;
    }

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

    private Class<?> forClass(ModelContext context) {
        return typeResolver.resolve(context.getType()).getErasedType();
    }


    @Override
    public boolean supports(DocumentationType delimiter) {
        return pluginDoesApply(delimiter);
    }
}
