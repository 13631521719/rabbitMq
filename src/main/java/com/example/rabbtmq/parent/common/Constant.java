package com.example.rabbtmq.parent.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static final String ROUTING_KEY_DIRECT="ROUTING_KEY_DIRECT";

    public static final String ROUTING_KEYS_TOPIC_PREFIX="ROUTING_KEY.";
    //TopicExchange交换机支持使用通配符*、#,*号只能向后多匹配一层路径。 #号可以向后匹配多层路径。注意区分路径层次的关键是点 .
    public static final String ROUTING_KEYS_TOPIC=ROUTING_KEYS_TOPIC_PREFIX+"#";
    public static final String QUEUE_NAME_TOPIC="QUEUE_NAME_TOPIC";
    public static final String EXCHANGE_NAME_TOPIC="EXCHANGE_NAME_TOPIC";



    public static final String ROUTING_KEYS_TOPIC_CORE_PREFIX="ROUTING_KEY_CORE.";

    //TopicExchange交换机支持使用通配符*、#,*号只能向后多匹配一层路径。 #号可以向后匹配多层路径。注意区分路径层次的关键是点 .
    public static final String ROUTING_KEYS_TOPIC_CORE=ROUTING_KEYS_TOPIC_CORE_PREFIX+"#";
    public static final String QUEUE_NAME_TOPIC_CORE="QUEUE_NAME_TOPIC_CORE";
    public static final String EXCHANGE_NAME_TOPIC_CORE="EXCHANGE_NAME_TOPIC_CORE";


    public static final Map<String,Object> ROUTING_KEYS_HEADERS=new HashMap(){{
        put("type", "cash");
        put("aging", "fast");
    }};
    public static final String ROUTING_KEYS_KEY="ROUTING_KEYS_KEY";
    public static final String QUEUE_NAME_HEADER="QUEUE_NAME_HEADER";
    public static final String EXCHANGE_NAME_HEADER="EXCHANGE_NAME_HEADER";

    public static final String ROUTING_KEYS_FANOUT="ROUTING_KEYS_FANOUT";
    public static final String QUEUE_NAME_FANOUT="QUEUE_NAME_FANOUT111";
    public static final String QUEUE_NAME_FANOUT_CORE="QUEUE_NAME_FANOUT_CORE111";
    public static final String EXCHANGE_NAME_FANOUT="EXCHANGE_NAME_FANOUT";


    public static final String QUEUE_NAME="QUEUE_NAME";
    public static final String EXCHANGE_NAME="EXCHANGE_NAME";
}
