package com.example.rabbtmq.parent.listener;

import com.example.rabbtmq.parent.common.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * TopicExchange绑定交换机是按规则转发消息，是交换机中最灵活的一个。也是最常用的一个，需要设置匹配的key，
 * 	使用通配符*、#,*号只能向后多匹配一层路径。 #号可以向后匹配多层路径。注意区分路径层次的关键是点 .
 */
@Component
@RabbitListener(queues = Constant.QUEUE_NAME_TOPIC)
public class TopicListener {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("TopicListener 收到消息，key=:"+Constant.ROUTING_KEYS_TOPIC+",消息内容为："+msg);
    }
}
