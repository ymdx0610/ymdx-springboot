package com.ymdx.web;

import com.ymdx.pojo.User;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 16:09
 * @Version: 1.0
 **/
@RestController
public class TestController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/search")
    public List<User> findDoc(){
        // 构造条件
        QueryBuilder builder = QueryBuilders.existsQuery("first_name");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        List<User> userList = elasticsearchTemplate.queryForList(searchQuery, User.class);
        return userList;
    }

}
