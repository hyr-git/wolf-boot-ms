package com.mlj.rabbitmq.utils;

import java.util.UUID;

public class UuidUtils {

	public static String getUuid() {
		return String.valueOf(UUID.randomUUID()).replaceAll("-", "");
	} 
}
