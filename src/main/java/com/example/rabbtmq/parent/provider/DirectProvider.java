package com.example.rabbtmq.parent.provider;

import com.example.rabbtmq.parent.common.TagConstant;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DirectProvider {
    @Autowired
    private ChannelUtil channelUtil;

    private Channel channel;



    public void sendMsg(String message) throws Exception {
        if (channel == null || !channel.isOpen())
            channel = channelUtil.createDirectChannel();
        channel.queueDeclare(TagConstant.DIRECT_QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", TagConstant.DIRECT_QUEUE_NAME, null, message.getBytes());
        System.out.println("==============================发送消息，消息内容为："+message);
    }
}
