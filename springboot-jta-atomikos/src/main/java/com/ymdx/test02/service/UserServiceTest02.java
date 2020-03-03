package com.ymdx.test02.service;

import com.ymdx.test01.mapper.UserMapperTest01;
import com.ymdx.test02.mapper.UserMapperTest02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceTest02 {

	@Autowired
	private UserMapperTest01 userMapperTest01;

	@Autowired
	private UserMapperTest02 userMapperTest02;

	@Transactional(transactionManager = "test2TransactionManager", rollbackFor = Exception.class)
	public int insertUserToTest2(String name, Integer age) {
		int insertUserToTest2Result = userMapperTest02.insert(name, age);
		log.info("######insertUserToTest2Result:{}##########", insertUserToTest2Result);
		int i = 1 / age;
		return insertUserToTest2Result;
	}

	@Transactional(rollbackFor = Exception.class)
	public int insertUserToTest01AndTest02(String name, Integer age) {
		// 传统分布式事务解决方案：jta+atomikos，注册到同一个全局事务中
		// 第一个数据源
		int insertUserResult01 = userMapperTest01.insert(name, age);
		// 第二个数据源
		int insertUserResult02 = userMapperTest02.insert(name, age);
//		int i = 1 / 0;
		int result = insertUserResult01 + insertUserResult02;
		return result;
	}

}
