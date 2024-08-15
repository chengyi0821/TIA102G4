package com.tia102g4.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	private static JedisPool pool = null;

	public static void main(String[] args) {
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		System.out.println(jedis.ping());
		System.out.println("Hello Jedis!");

		jedis.close();
		JedisUtil.shutdownJedisPool();
	}
}
