package com.example.rabbtmq.parent.listener;


import com.example.rabbtmq.parent.common.Constant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author niunafei
 * @function
 * @email niunafei0315@163.com
 * @date 2020/4/28  7:20 PM
 * @RabbitListener 自定义监听事件
 * @QueueBinding 绑定交换器与队列的关系value 指定队列exchange指定交换器
 * value= @Queue 指定配置队列的信息 value队列名称 autoDelete是否是临时队列
 * exchange= @Exchange 指定交换器 value指定交换器名称 type交换器类型
 * key  指定路由键
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = Constant.QUEUE_NAME, autoDelete = "true"),
                exchange = @Exchange(value = Constant.EXCHANGE_NAME, type = ExchangeTypes.FANOUT))
)
public class Listener {



    /**
     * 设置监听方法
     *  @RabbitHandler 声明监听方法是下面的 isDefault属性是默认false接受的完整对象，true接受body体
     *
     * @param msg
     */
    @RabbitHandler(isDefault = true)
    public void process(String msg) {
        System.out.println("Listener接受到消息：sms "+ msg);
    }




}
