package com.example.rabbtmq.parent.consumer;

import com.example.rabbtmq.parent.common.TagConstant;
import com.example.rabbtmq.parent.provider.ChannelUtil;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Service
public class TopicConsumer {
    @Autowired
    private ChannelUtil channelUtil;


    @Bean
    public Consumer consumer() throws Exception {
        Consumer consumer = null;
        Channel channel = channelUtil.createTopicChannel(TagConstant.TOPIC_EXCHANGE_NAME);
        //3.声明队列
        channel.queueDeclare(TagConstant.TOPIC_QUEUE_NAME, false, false, false, null);
        //4.绑定队列到交换器,指定路由key为update
        channel.queueBind(TagConstant.TOPIC_QUEUE_NAME, TagConstant.TOPIC_EXCHANGE_NAME, TagConstant.TOPIC_ROUTING_KEY);
        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);
        //这里重写了DefaultConsumer的handleDelivery方法，因为发送的时候对消息进行了getByte()，在这里要重新组装成String
        consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, "UTF-8");
                System.out.println("topic  consumer:" + message+",key="+envelope.getRoutingKey());
            }
        };
        //声明队列中被消费掉的消息（参数为：队列名称；消息是否自动确认;consumer主体）
        channel.basicConsume(TagConstant.TOPIC_QUEUE_NAME, true, consumer);
        return consumer;
    }

}
