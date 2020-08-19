package com.example.rabbtmq.parent.provider;

import com.example.rabbtmq.parent.common.TagConstant;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Service
public class FanoutProvider {
    @Autowired
    private ChannelUtil channelUtil;

    private Channel channel;


    public void sendMsg(String message) throws Exception {
        if (channel == null || !channel.isOpen())
            channel = channelUtil.createFanoutChannel(TagConstant.FANOUT_EXCHANGE_NAME);
        //消息发布（参数为：交换机名称; routingKey，忽略。在广播模式中，生产者声明交换机的名称和类型即可）
        channel.basicPublish(TagConstant.FANOUT_EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("********Message********:发送成功");

    }
}
