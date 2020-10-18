package com.mlj.ws.mq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mlj.rabbitmq.mq.config.DirectRabbitConfig;

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
	private AmqpTemplate rabbitTemplate;
	
	public void sendMsg(String msg) {
		String sengMsg = "hello, " + new Date() +"------"+msg;
		log.info("DirectSender: "+sengMsg);
		rabbitTemplate.convertAndSend(DirectRabbitConfig.WORK_ORDER_QUEUE_NAME,sengMsg);
	}
}

