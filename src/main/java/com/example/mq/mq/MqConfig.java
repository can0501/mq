package com.example.mq.mq;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;


public class MqConfig {


//    //队列 起名：TestDirectQueue
//    @Bean
//    public Queue TestDirectQueue() {
//        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
//        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
//        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
//        //   return new Queue("TestDirectQueue",true,true,false);
//
//        //一般设置一下队列的持久化就好,其余两个就是默认false
//        return new Queue("TestDirectQueue",true);
//    }
//
//    //Direct交换机 起名：TestDirectExchange
//    @Bean
//    DirectExchange TestDirectExchange() {
//        //  return new DirectExchange("TestDirectExchange",true,true);
//        return new DirectExchange("TestDirectExchange",true,false);
//    }
//
//    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
//    @Bean
//    Binding bindingDirect() {
//        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
//    }
//
//
//    @Bean
//    DirectExchange lonelyDirectExchange() {
//        return new DirectExchange("lonelyDirectExchange");
//    }

    public static int solution(String input) {
        // 在这⾥写代码
        // 请在最后return符合要求的子串个数
        int q = 0;
        for (int i = 0; i < input.length() - 1; ) {
            int l = i;
            int r = i + 1;

            if (input.charAt(l) == input.charAt(r)) {
                char c = input.charAt(l);
                if (c == 'a' || c == 'b') {
                    if (l > 0) {
                        if (input.charAt(l - 1) == c) {
                            i++;
                            continue;
                        }
                    }
                    if (r + 1 < input.length() - 1) {
                        if (input.charAt(r + 1) == c) {
                            i++;
                            continue;
                        }
                    }
                    q++;
                    i = i + 2;
                    continue;
                }

            }
            i++;


        }

        return q;

    }

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.println(bruteMethod(a));
    }

    public static int bruteMethod(int[] nums) {
        int max = nums[0];
        int t = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                for (int k = i; k <= j; k++) {
                    t += nums[k];
                }
                if (t > max)
                    max = t;
                t = 0;
            }
        }
        return max;
    }

    @Test
    @DisplayName("长数据测试")
    void testSolution4() {
    }


}
