package com.ymdx.test01.service;

import com.ymdx.test01.mapper.UserMapperTest01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceTest01 {
	@Autowired
	private UserMapperTest01 userMapperTest01;

	@Transactional(transactionManager = "test1TransactionManager", rollbackFor = Exception.class)
	public int insertUserToTest1(String name, Integer age) {
		int insertUserToTest1Result = userMapperTest01.insert(name, age);
		log.info("######insertUserToTest1Result:{}##########", insertUserToTest1Result);
		int i = 1 / age;
		return insertUserToTest1Result;
	}

}
