package com.example.rabbtmq.parent.listener;

import com.example.rabbtmq.parent.common.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = Constant.QUEUE_NAME_FANOUT_CORE)
public class FanoutCoreListener {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("FanoutCoreListener 收到消息，key=:"+Constant.ROUTING_KEYS_TOPIC+",消息内容为："+msg);
    }
}
