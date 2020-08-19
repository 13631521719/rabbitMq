package com.example.rabbtmq.parent.listener;

import com.example.rabbtmq.parent.common.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * HeadersExchange交换机是根据请求消息中设置的header attribute参数类型来匹配的（和routingKey没有关系）
 */
@Component
@RabbitListener(queues = Constant.QUEUE_NAME_HEADER)
public class HeadersListener {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("HeadersListener 收到消息，消息内容为："+msg);
    }
}
