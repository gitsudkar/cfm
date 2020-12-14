package com.sk.addressbook.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sk.addressbook.bean.SKContact;
import com.sk.addressbook.bean.SKUser;

public class DBUtil {
	private static Logger logger = LoggerFactory.getLogger(DBUtil.class);
	private static Gson gson = new Gson();

	public static List<SKContact> getAllContacts() {
		List<SKContact> contacts = new ArrayList<SKContact>();

		Collection<String> collection = Redis.getInstance().findAll("addressBook").values();
		logger.info("Size of collection:" + collection.size());

		for (String s : collection) {
			logger.debug("Adding to collection:" + s);
			SKContact contact = gson.fromJson(s, SKContact.class);
			contacts.add(contact);
		}
		return contacts;
	}

	public static long saveContact(String key, SKContact contact) {
		String contactJson = gson.toJson(contact);
		Redis redis = Redis.getInstance();
		return redis.save("addressBook",key, contactJson);
	}

	public static SKContact getContact(String key) {
		SKContact skContact = gson.fromJson(Redis.getInstance().get("addressBook",key), SKContact.class);
		return skContact;
	}
	
	public static long deleteContact(String key) {
		return Redis.getInstance().delete("addressBook",key);
	}
	
	public static boolean validateCreds(String userid,String password) {
		logger.debug("Recieved request for validating creds");
		 SKUser  skUser = gson.fromJson(Redis.getInstance().get("user",userid), SKUser.class);
		 if(skUser.getPassword().equals(password)) {
			 return true;
		 }
		return false;
	}

}
