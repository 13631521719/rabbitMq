package com.example.rabbtmq.parent.provider;

import com.example.rabbtmq.parent.common.TagConstant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TopicProvider {
    @Autowired
    private ChannelUtil channelUtil;

    private Channel channel;



    public void sendMsg(String message,String key) throws Exception {
        if (channel == null || !channel.isOpen())
            channel = channelUtil.createTopicChannel(TagConstant.TOPIC_EXCHANGE_NAME);
        //5.发布消息
        channel.basicPublish(TagConstant.TOPIC_EXCHANGE_NAME, TagConstant.TOPIC_ROUTING_KEY+key, null, message.getBytes());
    }
}
