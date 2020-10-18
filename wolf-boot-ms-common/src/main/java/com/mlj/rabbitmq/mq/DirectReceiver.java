package com.mlj.rabbitmq.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mlj.rabbitmq.mq.config.DirectRabbitConfig;




/***
 * 消费者
 * @author m1832
 *
 */
@Component
@RabbitListener(queues=DirectRabbitConfig.DIRECT_QUEUE_NAME)//监听指定的队列
public class DirectReceiver {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@RabbitHandler
	public void process(String msg) {
		//log.info("DirectReceiver receive: "+msg);	
		rabbitTemplate.convertAndSend(DirectRabbitConfig.WORK_ORDER_QUEUE_NAME,msg);
	}
}
