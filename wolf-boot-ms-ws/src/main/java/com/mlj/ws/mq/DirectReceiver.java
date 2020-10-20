package com.mlj.ws.mq;

import java.io.IOException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mlj.common.mq.config.DirectRabbitConfig;
import com.mlj.ws.websocket.server.WebSocketServer;

import lombok.extern.slf4j.Slf4j;


/***
 * 消费者
 * @author m1832
 *
 */
@Slf4j
@Component
@RabbitListener(queues=DirectRabbitConfig.DIRECT_QUEUE_NAME)//监听指定的队列
public class DirectReceiver {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@RabbitHandler
	public void process(String msg) throws IOException {
		
		//获取消息接受者用户id
		String userId = "";
		WebSocketServer.sendInfoByUserId(msg, userId);
		
		log.info("DirectReceiver receive: "+msg);	
		rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_QUEUE_NAME,msg);
	}
}
