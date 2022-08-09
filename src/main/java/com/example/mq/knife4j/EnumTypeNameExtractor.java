//package com.example.mq.knife4j;
//
//import com.fasterxml.classmate.TypeResolver;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.springframework.plugin.core.PluginRegistry;
//import springfox.documentation.schema.TypeNameExtractor;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.schema.EnumTypeDeterminer;
//import springfox.documentation.spi.schema.TypeNameProviderPlugin;
//import springfox.documentation.spi.schema.contexts.ModelContext;
//
//import java.util.List;
//
///**
// * @author 钟金灿
// * @since 2022/3/30
// */
//
//public class EnumTypeNameExtractor extends TypeNameExtractor {
//    private final TypeResolver typeResolver;
//    private final PluginRegistry<TypeNameProviderPlugin, DocumentationType> typeNameProviders;
//    private final EnumTypeDeterminer enumTypeDeterminer;
//
//    public EnumTypeNameExtractor(TypeResolver typeResolver, PluginRegistry<TypeNameProviderPlugin, DocumentationType> typeNameProviders, EnumTypeDeterminer enumTypeDeterminer) {
//        super(typeResolver, typeNameProviders, enumTypeDeterminer);
//    }
//
//    @Override
//    public String typeName(ModelContext context) {
//
//        return super.typeName(context);
//    }
//}
