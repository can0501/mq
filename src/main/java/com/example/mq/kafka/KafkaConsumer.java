package com.example.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 犬小哈（公众号：小哈学Java）
 * @date 2019/4/12
 * @time 下午3:05
 * @discription 消息消费者
 **/
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "xiaoha", groupId = "group_id")
    public void consume(List<String> record, Acknowledgment ack) {
        //log.info("## consume message: topic {} offset {}", record.topic(),record.offset());
        final String message = record.get(0);

        log.info("kafka consume:{}  [{}]", record.size(),record);
        //ack.acknowledge();
    }
}
