package com.mlj.rabbitmq.utils;

import redis.clients.jedis.Jedis;

public class JedisUtils {

	
	private static Jedis jedis = new Jedis("dev.carsir.xin", 6379);
	static {
		 jedis.auth("devTaG8ie0)fE");
	}
	
	public Jedis getJedis() {
	    return jedis;
	}
}
