package com.mlj.rabbitmq.mq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class DirectRabbitConfig {

	public final static String DIRECT_QUEUE_NAME = "direct";
	public final static String WORK_ORDER_QUEUE_NAME = "workOrder";
	
	@Bean
	public Queue directQueue() {
		return new Queue(DIRECT_QUEUE_NAME);
	}
	
	@Bean
	public Queue workOrderQueue() {
		return new Queue("WORK_ORDER_QUEUE_NAME");
	}
}
