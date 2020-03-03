package com.ymdx.controller;

import com.ymdx.test01.service.UserServiceTest01;
import com.ymdx.test02.service.UserServiceTest02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisMultilDataSourceController {

	@Autowired
	private UserServiceTest01 userServiceTest01;

	@Autowired
	private UserServiceTest02 userServiceTest02;

	@RequestMapping("/insertUserTest1")
	public Integer insertUserTest1(String name, Integer age) {
		return userServiceTest01.insertUserToTest1(name, age);
	}

	@RequestMapping("/insertUserTest2")
	public Integer insertUserTest2(String name, Integer age) {
		return userServiceTest02.insertUserToTest2(name, age);
	}

	@RequestMapping("/insertUserTest01AndTest02")
	public int insertUserTest01AndTest02(String name, Integer age) {
		return userServiceTest02.insertUserToTest01AndTest02(name, age);
	}

}
