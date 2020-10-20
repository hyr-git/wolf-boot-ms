package com.mlj.rabbitmq.consumer;

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