package com.mlj.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.mlj.rabbitmq.mq.config.DirectRabbitConfig;

import lombok.extern.slf4j.Slf4j;


/***
 * 消费者2  模拟多消费端(多个消费端共同处理所有的消息)
 * @author m1832
 *
 */
/*@Slf4j
@Component
@RabbitListener(queues=DirectRabbitConfig.WORK_ORDER_QUEUE_NAME)//监听指定的队列
public class DirectWorkOrderReceiver {

	@RabbitHandler
	public void process(String msg) {
		log.info("DirectReceiver2 receive: "+msg);	
	}
}
*/