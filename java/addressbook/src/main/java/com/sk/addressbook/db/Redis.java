package com.sk.addressbook.db;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sk.addressbook.AddressBookApplication;
import com.sk.addressbook.bean.SKContact;

import redis.clients.jedis.Jedis;

public class Redis {
	Logger logger = LoggerFactory.getLogger(Redis.class);

	private static Redis redis = null;
	private Jedis jedis;

	private Redis() {
		jedis = new Jedis("localhost", 6379);
	}

	public static Redis getInstance() {
		if (redis == null) {
			redis = new Redis();
		}
		return redis;
	}

	public Map<String, String> findAll(String realm) {
		Map<String, String> map = jedis.hgetAll(realm);
		logger.info(map.keySet().toString());
		return map;
	}

	public long save(String realm,String key,String contact) {
		return jedis.hset(realm, key, contact);
	}

	public long delete(String realm,String key) {
		return jedis.hdel(realm, key);
	}

	public String get(String realm, String key) {
		String contact = jedis.hget(realm, key);
		return contact;
	}
	
	
}
