package com.ymdx.pojo;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @ClassName: UserNode
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:29
 * @Version: 1.0
 **/
@NodeEntity(label = "User")
public class UserNode {

    @Id
    @GeneratedValue
    private Long nodeId;

    @Property
    private String userId;

    @Property
    private String userName;

    @Property
    private int age;

    // 从 Neo4j API 2.0.5开始需要无参构造函数
    public UserNode(){}

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
