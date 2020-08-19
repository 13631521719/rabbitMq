package com.example.rabbtmq.parent.common;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQProvider {

    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    private Channel channel;
    private static final String EXCHANGE_NAME="exchange";
    public void sendMsg(String message) throws Exception {
        if (channel == null || !channel.isOpen())
            channel = createChannel();
        //消息发布（参数为：交换机名称; routingKey，忽略。在广播模式中，生产者声明交换机的名称和类型即可）
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("********Message********:发送成功");

    }

    public Channel createChannel() throws Exception {
        //New一个RabbitMQ的连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置需要连接的RabbitMQ地址，这里指向本机
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        //尝试获取一个连接
        Connection connection = factory.newConnection();
        //尝试创建一个channel
        channel = connection.createChannel();
        //声明交换机（参数为：交换机名称; 交换机类型，广播模式）
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        return channel;
    }

    @Bean
    public Consumer consumer()throws Exception {
        Consumer consumer = null;
        try {

            Channel channel = createChannel();
            //交换机声明（参数为：交换机名称；交换机类型）
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            //获取一个临时队列
            String queueName = channel.queueDeclare().getQueue();
            //队列与交换机绑定（参数为：队列名称；交换机名称；routingKey忽略）
            channel.queueBind(queueName, EXCHANGE_NAME, "");
            //这里重写了DefaultConsumer的handleDelivery方法，因为发送的时候对消息进行了getByte()，在这里要重新组装成String
            consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    String message = new String(body, "UTF-8");
                    System.out.println("received:" + message);
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
