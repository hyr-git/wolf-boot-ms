package com.mlj.common.mq.modle;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MQMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String context;
	private String fromUserId;
	private String toUserId;
	private Date createdDate;
	private Date readDate;
	
	public MQMessage(){
		
	}
}
