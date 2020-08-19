package com.example.rabbtmq.parent.provider;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ChannelUtil {
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;

    private Connection createConnection()throws Exception{
        //New一个RabbitMQ的连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置需要连接的RabbitMQ地址，这里指向本机
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        //尝试获取一个连接
        Connection connection = factory.newConnection();
        return connection;
    }
    public Channel createFanoutChannel(String EXCHANGE_NAME) throws Exception {
        //尝试获取一个连接
        Connection connection = createConnection();
        //尝试创建一个channel
        Channel channel = connection.createChannel();
        //声明交换机（参数为：交换机名称; 交换机类型，广播模式）
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        return channel;
    }

    public Channel createDirectChannel() throws Exception {
        //尝试获取一个连接
        Connection connection = createConnection();
        //尝试创建一个channel
        Channel channel = connection.createChannel();
        return channel;
    }

    public Channel createTopicChannel(String EXCHANGE_NAME) throws Exception {
        //尝试获取一个连接
        Connection connection = createConnection();
        //尝试创建一个channel
        Channel channel = connection.createChannel();
        //声明交换机（参数为：交换机名称; 交换机类型，广播模式）
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        return channel;
    }
}
