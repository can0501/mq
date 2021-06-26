package com.example.mq.线程池;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.concurrent.*;


//@Configuration
public class 线程池的test {

    @Autowired
    private Environment env;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;


    @Bean("crmConnectionFactory")
    @Primary
    public ConnectionFactory getCrmConnectionFactory() {
        CachingConnectionFactory baseConnectionFactory = getBaseConnectionFactory();
//        baseConnectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.crm.virtual-host"));
        return baseConnectionFactory;
    }

    @Bean("ewsConnectionFactory")
    public ConnectionFactory getEwsConnectionFactory() {
        CachingConnectionFactory baseConnectionFactory = getBaseConnectionFactory();
//        baseConnectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.ews.virtual-host"));
        return baseConnectionFactory;
    }

    public CachingConnectionFactory getBaseConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("192.168.126.128");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
//        cachingConnectionFactory.setPublisherReturns(true);
        cachingConnectionFactory.setConnectionTimeout(10000);
        cachingConnectionFactory.setChannelCheckoutTimeout(10000);
        return cachingConnectionFactory;
    }


    @Bean("crmRabbitListener")
    @ConfigurationProperties(prefix = "spring.rabbitmq.listener")
    public RabbitListenerContainerFactory getListenerContainerFactory(@Qualifier("crmConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(simpleRabbitListenerContainerFactory, connectionFactory);
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        return simpleRabbitListenerContainerFactory;
    }

    @Bean("ewsRabbitTemplate")
    public RabbitTemplate ewsrabbitTemplate(@Qualifier("ewsConnectionFactory") ConnectionFactory connectionFactory) throws IOException {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        // 消息发送到交换器Exchange后触发回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            log.info("消息唯一标识: {}, 确认结果: {}", correlationData.getId(), ack);
//            System.out.println(String.format("消息唯一标识: {%s}, 确认结果: {%s}", correlationData.getId(), ack));
        });
        // 如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            String correlationId = message.getMessageProperties().getCorrelationId();
//            System.out.println(String.format("消息：{%s} 发送失败, 应答码：{%s} 原因：{%s} 交换机: {%s}  路由键: {%s}", correlationId, replyCode, replyText, exchange, routingKey));
        });
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();  // 最简单，不过不推荐
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1,  //核心
                1,                                                      //最大线程池
                1L,                                                       //存活时间
                TimeUnit.MICROSECONDS,                                                         //超时时间
                new LinkedBlockingDeque<>(10),                                      //队列
                Executors.defaultThreadFactory(),                                      //线程池工厂，维护线程名字，提供创建线程方法
                 new ThreadPoolExecutor.AbortPolicy());                                 //拒绝策列，默认是抛异常
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 1; i++) {
             threadPoolExecutor2.execute(() -> {

            });
        }

        Thread.sleep(500L);

        Thread.sleep(500L);
        for (int i = 0; i < 1; i++) {
            threadPoolExecutor2.execute(() -> {

            });
        }


    }

    static class my_syncQueue<E> extends SynchronousQueue<E> {
        @Override
        public boolean offer(E e) {
            return false;
        }
    }

}
