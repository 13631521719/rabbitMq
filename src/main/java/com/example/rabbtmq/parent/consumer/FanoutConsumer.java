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
public class FanoutConsumer {
    @Autowired
    private ChannelUtil channelUtil;


    @Bean(name = "FanoutConsumer2")
    public Consumer consumer()throws Exception {
        Consumer consumer = null;
        try {
            Channel channel = channelUtil.createFanoutChannel(TagConstant.FANOUT_EXCHANGE_NAME);
            //获取一个临时队列
            String queueName = channel.queueDeclare().getQueue();
            //队列与交换机绑定（参数为：队列名称；交换机名称；routingKey忽略）
            channel.queueBind(queueName, TagConstant.FANOUT_EXCHANGE_NAME, "");
            //这里重写了DefaultConsumer的handleDelivery方法，因为发送的时候对消息进行了getByte()，在这里要重新组装成String
            consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    String message = new String(body, "UTF-8");
                    System.out.println("fanout  consumer:" + message);
                }
            };
            //声明队列中被消费掉的消息（参数为：队列名称；消息是否自动确认;consumer主体）
            channel.basicConsume(queueName, true, consumer);
            //这里不能关闭连接，调用了消费方法后，消费者会一直连接着rabbitMQ等待消费
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return consumer;
    }

}
