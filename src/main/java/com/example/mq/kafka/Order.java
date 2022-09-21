package com.example.mq.kafka;

/**
 * @author 钟金灿
 * @since 2022/9/20
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 犬小哈（公众号：小哈学Java）
 * @date 2019/4/12
 * @time 下午3:05
 * @discription 订单实体类
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * 订单id
     */
    private long orderId;
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;
}
