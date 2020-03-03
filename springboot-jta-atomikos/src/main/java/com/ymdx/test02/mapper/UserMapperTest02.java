package com.ymdx.test02.mapper;

import com.ymdx.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapperTest02 {
	/**
	 * 根据名称查询用户
	 * @param name
	 * @return
	 */
	@Select("SELECT * FROM USERS WHERE NAME = #{name}")
	User findByName(@Param("name") String name);

	/**
	 * 添加用户
	 * @param name
	 * @param age
	 * @return
	 */
	@Insert("INSERT INTO USERS(NAME, AGE) VALUES(#{name}, #{age})")
	int insert(@Param("name") String name, @Param("age") Integer age);
}
