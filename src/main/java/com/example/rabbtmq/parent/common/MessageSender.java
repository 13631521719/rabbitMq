package com.example.rabbtmq.parent.common;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 直接发送消息
     * @param key 过滤的key
     * @param msg 消息体
     */
    public void sender(String key,String msg)throws AmqpException{
        System.out.println("==================================开始发送消息，发送内容："+msg);
        rabbitTemplate.convertAndSend(key, msg);
        //支持RPC调用，可以有返回值
        //Order order = (Order) rabbitTemplate.convertSendAndReceive("query.order", orderId);
    }
    /**
     * 直接发送消息
     * @param key 过滤的key
     * @param msg 消息体
     */
    public void senderObject(String key,MessageInfo msg)throws AmqpException{
        System.out.println("==================================开始发送消息，发送内容："+msg);
        rabbitTemplate.convertAndSend(key, msg);
    }

    /**
     * 发送消息到TOPIC交换机
     * @param exchangName 交换机名称
     * @param key 过滤的key
     * @param msg 消息体
     */
    public void senderExchang(String exchangName,String key,String msg)throws AmqpException {
        System.out.println("==================================开始发送消息，exchangName="+exchangName+",key="+key+",发送内容："+msg);
        rabbitTemplate.convertAndSend(exchangName, key, msg);
    }


    /**
     * 发送消息到header交换机
     * @param exchangName 交换机名称
     * @param key 过滤的key
     * @param head 需要携带的头
     * @param msg 消息体
     */
    public void senderHeader(String exchangName,String key,Map<String, Object> head, String msg)throws AmqpException{
        System.out.println("==================================开始发送消息，exchangName="+exchangName+",head="+head+",发送内容："+msg);
        rabbitTemplate.convertAndSend(exchangName, key, getMessage(head, msg));
    }

    /**
     * 发送消息到广播消息
     * @param msg
     */
    public void senderFanout(String msg){
        System.out.println("==================================开始发送消息，发送内容："+msg);
        //这里必须给routingKey ，任意值都可以
        rabbitTemplate.convertAndSend(Constant.EXCHANGE_NAME_FANOUT, "", msg);
    }
    /**
     * 发送消息到广播消息
     * @param msg
     */
    public void senderFanout2(String msg){
        System.out.println("==================================开始发送消息，发送内容："+msg);
        //这里必须给routingKey ，任意值都可以
        rabbitTemplate.convertAndSend(Constant.EXCHANGE_NAME, "", msg);
    }
    /**
     * 创建消息体
     * @param head
     * @param msg
     * @return
     */
    private Message getMessage(Map<String, Object> head, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        for (Map.Entry<String, Object> entry : head.entrySet()) {
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter.toMessage(msg, messageProperties);
    }


}
