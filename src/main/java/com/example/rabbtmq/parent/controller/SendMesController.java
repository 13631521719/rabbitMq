package com.example.rabbtmq.parent.controller;

import com.example.rabbtmq.parent.common.Constant;
import com.example.rabbtmq.parent.common.MessageInfo;
import com.example.rabbtmq.parent.common.RabbitMQProvider;
import com.example.rabbtmq.parent.provider.DirectProvider;
import com.example.rabbtmq.parent.provider.FanoutProvider;
import com.example.rabbtmq.parent.provider.TopicProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SendMesController {
    @Autowired
    private DirectProvider directProvider;
    @Autowired
    private FanoutProvider fanoutProvider;
    @Autowired
    private TopicProvider topicProvider;
    @Autowired
    private RabbitMQProvider provider;

    @RequestMapping(value = "/send/testDirect",method = RequestMethod.GET)
    public @ResponseBody Map testDirect()throws Exception{
        directProvider.sendMsg("发送一个消息试试看");
        Map<String,Object> res=new HashMap<>();
        res.put("code",200);
        res.put("message","消息发送成功");
        return res;
    }

    @RequestMapping(value = "/send/testTopic",method = RequestMethod.GET)
    public @ResponseBody Map testTopic(@RequestParam(name = "key") String key)throws Exception{
        topicProvider.sendMsg("发送一个topic消息",key);
        Map<String,Object> res=new HashMap<>();
        res.put("code",200);
        res.put("message","消息发送成功");
        return res;
    }


    @RequestMapping(value = "/send/testTopicCore",method = RequestMethod.GET)
    public @ResponseBody Map testTopicCore(@RequestParam(name = "key") String key)throws Exception{
        //messageSender.senderExchang(Constant.EXCHANGE_NAME_TOPIC_CORE,(Constant.ROUTING_KEYS_TOPIC_CORE_PREFIX+key),"这是testTopicCore测试消息发送");
        Map<String,Object> res=new HashMap<>();
        res.put("code",200);
        res.put("message","消息发送成功");
        return res;
    }

    @RequestMapping(value = "/send/testHeader",method = RequestMethod.GET)
    public @ResponseBody Map testHeader()throws Exception{
       // messageSender.senderHeader(Constant.EXCHANGE_NAME_HEADER,                Constant.ROUTING_KEYS_KEY,                Constant.ROUTING_KEYS_HEADERS,                "这是testHeader测试消息发送");
        Map<String,Object> res=new HashMap<>();
        res.put("code",200);
        res.put("message","消息发送成功");
        return res;
    }

    @RequestMapping(value = "/send/testFanout",method = RequestMethod.GET)
    public @ResponseBody Map testFanout(@RequestParam(name="message")String message)throws Exception{
        fanoutProvider.sendMsg(message);
        Map<String,Object> res=new HashMap<>();
        res.put("code",200);
        res.put("message","消息发送成功");
        return res;
    }
}
