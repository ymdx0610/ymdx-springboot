package com.ymdx.dao;

import com.ymdx.pojo.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName: UserRelationRepository
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:31
 * @Version: 1.0
 **/
public interface UserRelationRepository extends Neo4jRepository<UserRelation, Long> {

    @Query("MATCH p=(n:User)<-[r:UserRelation]->(n1:User) where n.userId={firstUserId} and n1.userId={secondUserId} return p")
    List<UserRelation> findUserRelationByEachId(@Param("firstUserId") String firstUserId, @Param("secondUserId") String secondUserId);

    @Query("MATCH (fu:User),(su:User) where fu.userId={firstUserId} and su.userId={secondUserId} CREATE p=(fu)-[r:UserRelation]->(su) return p")
    List<UserRelation> addUserRelation(@Param("firstUserId") String firstUserId, @Param("secondUserId") String secondUserId);

}