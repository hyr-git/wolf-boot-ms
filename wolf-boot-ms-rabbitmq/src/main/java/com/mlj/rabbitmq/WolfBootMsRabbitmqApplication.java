package com.mlj.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/****
 * MQ消息转发器---接受webSocket消息转发通过广播进行通知
 * @author m1832
 *
 */
@SpringBootApplication
public class WolfBootMsRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(WolfBootMsRabbitmqApplication.class, args);
	}

}
