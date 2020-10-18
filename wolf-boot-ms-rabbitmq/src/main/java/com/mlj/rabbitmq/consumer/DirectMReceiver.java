package com.mlj.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mlj.rabbitmq.mq.config.DirectRabbitConfig;

import lombok.extern.slf4j.Slf4j;


/***
 * 消费者
 * @author m1832
 *
 */
@Slf4j
@Component
@RabbitListener(queues=DirectRabbitConfig.DIRECT_QUEUE_NAME)//监听指定的队列
public class DirectMReceiver {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RabbitHandler
	public void process(String msg) {
		log.info("DirectReceiver receive: "+msg);	
		rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_QUEUE_NAME,msg);
	}
}
