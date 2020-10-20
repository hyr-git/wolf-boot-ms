package com.mlj.rabbitmq.deadqueue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mlj.rabbitmq.utils.UuidUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DeadProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/sendDeadLatter")
    public void sendMessage(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email","11111111111");
        jsonObject.put("timestamp",System.currentTimeMillis());

        String json = jsonObject.toJSONString();
        log.info(json);
        
        Message message = MessageBuilder.withBody(json.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("UTF-8").setMessageId(UuidUtils.getUuid()).build();
        amqpTemplate.convertAndSend(RabbitDeadQueueConfig.DIRECT_EXCHANGE_NAME,RabbitDeadQueueConfig.DIRECT_QUEUE_NAME,message);
    }

}