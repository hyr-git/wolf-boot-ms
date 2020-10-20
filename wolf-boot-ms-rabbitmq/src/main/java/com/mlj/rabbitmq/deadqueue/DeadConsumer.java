package com.mlj.rabbitmq.deadqueue;



import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;


/****
 * 幂等操作---死信队列
 * @author m1832
 *
 */
@Component
@Slf4j
public class DeadConsumer {


    @RabbitListener(queues = RabbitDeadQueueConfig.DIRECT_QUEUE_NAME)
    public void receiveMessage(Message message,@Headers Map<String,Object> headers, Channel channel) throws Exception {

    	Jedis jedis = new Jedis("dev.carsir.xin", 6379);
        jedis.auth("devTaG8ie0)fE");
         
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(),"UTF-8");
        log.info("接收导的消息为："+msg+"==消息id为："+messageId);

        String messageIdRedis = jedis.get("messageId");

        if(messageId == messageIdRedis){
            return;
        }
        
        try {
        	/* JSONObject jsonObject = JSONObject.parseObject(msg);
             String email = jsonObject.getString("email");
             String content = jsonObject.getString("timestamp");*/

             /*String httpUrl = "http://127.0.0.1:8080/email?email"+email+"&content="+content;
             // 如果发生异常则返回null
             String body = HttpUtils.httpGet(httpUrl, "utf-8");
             //
             if(body == null){
                 throw new Exception();
             }*/
             
             int i=1/0;
             
             jedis.set("messageId",messageId);
             Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
     		 //手动签收
             channel.basicAck(deliveryTag,false);
		} catch (Exception e) {
			 //尝试让消费者消费的时候发生异常。然后在catch块中拒绝消息
			 // 拒绝消息，给死信队列
	         channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
		}
    }
}