package com.ymdx.pojo;

import org.neo4j.ogm.annotation.*;

/**
 * @ClassName: UserRelation
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 09:29
 * @Version: 1.0
 **/
@RelationshipEntity(type = "UserRelation")
public class UserRelation {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private UserNode startNode;

    @EndNode
    private UserNode endNode;

    // 从 Neo4j API 2.0.5开始需要无参构造函数
    public UserRelation(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserNode getStartNode() {
        return startNode;
    }

    public void setStartNode(UserNode startNode) {
        this.startNode = startNode;
    }

    public UserNode getEndNode() {
        return endNode;
    }

    public void setEndNode(UserNode endNode) {
        this.endNode = endNode;
    }

}
