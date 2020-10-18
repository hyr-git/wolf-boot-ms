package com.mlj.ws.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DirectSimpleRabbitConfig {

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
