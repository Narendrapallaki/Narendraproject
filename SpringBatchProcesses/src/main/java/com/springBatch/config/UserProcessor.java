package com.springBatch.config;

import org.springframework.batch.item.ItemProcessor;

import com.springBatch.entity.Users;

public class UserProcessor implements ItemProcessor<Users, Users> {

	@Override
	public Users process(Users item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
