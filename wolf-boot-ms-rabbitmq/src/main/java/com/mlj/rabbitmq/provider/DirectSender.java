package com.mlj.rabbitmq.provider;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mlj.common.mq.config.DirectRabbitConfig;

import lombok.extern.slf4j.Slf4j;

/***
 * 生产者
 * @author m1832
 *
 */
@Slf4j
@Component
public class DirectSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMsg(String msg) {
		String sengMsg = "hello, " + new Date() +"------"+msg;
		log.info("DirectSender: "+sengMsg);
		rabbitTemplate.convertAndSend(DirectRabbitConfig.WORK_ORDER_QUEUE_NAME,sengMsg);
	}
}

