//package com.example.mq.spring;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author 钟金灿
// * @since 2022/9/10
// */
//
////InstantiationAwareBeanPostProcessor 实例化前后置处理器
////@Component
//public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
//    @Override
//    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        if (beanName.equals("aaService") || beanClass == AaService.class) {
//            AaService aaService = new AaService();
////            aaService.bbService = new BbService();
//            aaService.sd = "InstantiationAwareBeanPostProcessor 实例化前直接返回实例 提前返回实例则不会走后续的生命周期";
//            return aaService;
//        }
//        return null;
//
//    }
//}
