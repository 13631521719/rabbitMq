package com.example.rabbtmq.parent.consumer;

import com.example.rabbtmq.parent.common.TagConstant;
import com.example.rabbtmq.parent.provider.ChannelUtil;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Configuration
public class DirectConsumer {
    @Autowired
    private ChannelUtil channelUtil;


    @Bean(name = "directConsumer2")
    public Consumer directConsumer2()throws Exception {
        //创建通道
        Channel channel = channelUtil.createDirectChannel();
        //队列声明
        channel.queueDeclare(TagConstant.DIRECT_QUEUE_NAME, false, false, false, null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取到达的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body, "utf-8");
                System.out.println("================================direct consumer:" + msg);
            }
        };
        //监听队列
        channel.basicConsume(TagConstant.DIRECT_QUEUE_NAME, true, consumer);
        return consumer;
    }

}
