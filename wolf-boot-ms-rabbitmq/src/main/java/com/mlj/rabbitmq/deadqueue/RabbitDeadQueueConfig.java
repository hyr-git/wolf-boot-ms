package com.mlj.rabbitmq.deadqueue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/***
 * 成为私信队列的3中情况
 *     消息被拒绝,并且requeue=false
 *     消息TTL过期
 *     队列达到最大长度
 * @author m1832
 * 
 * 签收应答模式：手动签收与自动签收。
 *     自动签收---就是消费了一条消息就自动告诉队列删除消息。
 *         弊端--不管消费逻辑是否成功,都会删除消息,这样就会造成消息丢失。
 *    
 *     手动应答---消费逻辑处理成功后,手动告诉队列消费成功,然后再从队列中删除该消息。
 *     
 *     
 * 配置消费者开启手动签收模式：
 *    spring.rabbitmq.listener.simple.acknowledge-moode = manual
 */

@Configuration
public class RabbitDeadQueueConfig {

    public static final String DIRECT_QUEUE_NAME = "byte-direct-queue";
    public static final String DEAD_QUEUE_NAME = "byte-dead-queue";

    public static final String DEAD_EXCHANGE_NAME = "byte-dead-queue";
    public static final String DIRECT_EXCHANGE_NAME = "byte-direct-queue";

    public static final String DIRECT_ROUTING_KEY = "byte-direct-queue";
    public static final String DEAD_ROUTING_KEY = "byte-dead-queue";

    // 队列申明
    @Bean(value="byteQueue")
    public Queue queue(){
    	 Map<String,Object> map = new HashMap<>();
         map.put("x-dead-letter-exchange",DEAD_EXCHANGE_NAME);
         map.put("x-dead-letter-routing-key",DEAD_ROUTING_KEY);
         map.put("x-max-length",3); //模拟队列达到最大长度
//         map.put("x-message-ttl",7200); // 模拟队列过期时间
         Queue queue = new Queue(DIRECT_QUEUE_NAME,true,false,false,map);
        return queue;
    }

    // 申明交换机
    @Bean(value="byteDirectExchange")
    public DirectExchange directExchange(){
        //TODO 不通过配置,通过代码实现手动确认机制
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    // 数据绑定申明
    @Bean(value="byteDirectBinding")
    public Binding directBinding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(DIRECT_ROUTING_KEY);
    }
}