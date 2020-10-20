package com.mlj.rabbitmq.dto;

import lombok.Data;

@Data
public class ReadMessageDTO {

	private String readByUserId;
	
	private String messageId;
}
