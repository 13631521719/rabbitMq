package com.example.rabbtmq.parent.queue;

import com.example.rabbtmq.parent.common.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectQueue {
    //配置一个routingKey为ROUTING_KEY_DIRECT的消息队列
    @Bean
    public Queue paymentNotifyQueue() {
        return new Queue(Constant.ROUTING_KEY_DIRECT);
    }
}
