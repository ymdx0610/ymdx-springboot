package com.ymdx.dao;

import com.ymdx.pojo.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName: UserRepository
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:31
 * @Version: 1.0
 **/
public interface UserRepository extends Neo4jRepository<UserNode, Long> {

    @Query("MATCH (n: User) RETURN n")
    List<UserNode> getUserNodeList();

    @Query("CREATE (n: User{age:{age},userName:{userName}}) RETURN n")
    List<UserNode> addUserNodeList(@Param("userName") String userName, @Param("age") int age);

}