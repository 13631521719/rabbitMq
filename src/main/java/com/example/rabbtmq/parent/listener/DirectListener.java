package com.example.rabbtmq.parent.listener;


import com.example.rabbtmq.parent.common.Constant;
import com.example.rabbtmq.parent.common.MessageInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;



//1.direct模式：不需要绑定交换机，直接发送消息，消费型，只有一个服务可以消费到消息
@Component
@RabbitListener(queues = Constant.ROUTING_KEY_DIRECT)
public class DirectListener {
    public static final String uuid=UUID.randomUUID().toString();


    /**
     * 设置监听方法
     *
     * @param msg
     * @RabbitHandler 声明监听方法是下面的 isDefault属性是默认false接受的完整对象，true接受body体
     */
    @RabbitHandler(isDefault = true)
    public void receive(String msg) {

        System.out.println("DirectListener 收到消息，"+",消息内容为："+msg);
    }

    @RabbitHandler
    public void receive(MessageInfo msg) {
        System.out.println("DirectListener 收到消息，"+",消息内容为："+msg);
    }
}
