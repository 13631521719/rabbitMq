package com.example.rabbtmq.parent.config;

import com.example.rabbtmq.parent.common.Constant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BingConfig {
    /**
     * TOPIC普通测试 消费型
     * @return
     */
    @Bean
    public Queue topicQueue() {
        return new Queue(Constant.QUEUE_NAME_TOPIC);
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(Constant.EXCHANGE_NAME_TOPIC);
    }
    @Bean
    public Binding bindingPaymentExchange(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(Constant.ROUTING_KEYS_TOPIC);
    }

    /**
     * TOPIC CORE测试 消费型
     * @return
     */
    @Bean
    public Queue coreQueue() {
        return new Queue(Constant.QUEUE_NAME_TOPIC_CORE);
    }
    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange(Constant.EXCHANGE_NAME_TOPIC_CORE);
    }
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with(Constant.ROUTING_KEYS_TOPIC_CORE);
    }

    /**
     * HeadersExchange测试 消费型
     * @return
     */
    @Bean
    public Queue headersQueue() {
        return new Queue(Constant.QUEUE_NAME_HEADER);
    }
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(Constant.EXCHANGE_NAME_HEADER);
    }
    @Bean
    public Binding bindingCreditAExchange(Queue headersQueue, HeadersExchange headersExchange) {
        return BindingBuilder.bind(headersQueue)
                .to(headersExchange)
                .whereAll(Constant.ROUTING_KEYS_HEADERS)//.whereAny() 匹配任意一个，.whereAll 匹配所有
                .match();
    }

    /**
     * fanoutExchange测试  伪广播型
     * @return
     */
    @Bean
    public Queue fanoutQueue() {
        return new Queue(Constant.QUEUE_NAME_FANOUT);
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(Constant.EXCHANGE_NAME_FANOUT);
    }
    @Bean
    public Binding bindingReportPaymentExchange(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }


    @Bean
    public Queue fanoutCoreQueue() {
        return new Queue(Constant.QUEUE_NAME_FANOUT_CORE);
    }
    @Bean
    public Binding bindingReportRefundExchange(Queue fanoutCoreQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutCoreQueue).to(fanoutExchange);
    }
}
