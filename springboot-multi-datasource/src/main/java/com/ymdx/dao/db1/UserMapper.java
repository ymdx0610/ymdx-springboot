package com.ymdx.dao.db1;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @ClassName: UserMapper
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2019-08-26 10:08
 * @Version: 1.0.1
 **/
@Qualifier("db1SqlSessionFactory")
public interface UserMapper {

    /**
     * @Author: ymdx
     * @Description: 添加用户
     * @Date: 2019-08-26 10:09
     * @Param: [name, password]
     * @return: void
     **/
    @Insert("insert into t_user(name,password) values(#{name},#{password})")
    void addUser(@Param("name") String name, @Param("password") String password);

}
