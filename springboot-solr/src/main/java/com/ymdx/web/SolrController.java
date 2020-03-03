package com.ymdx.web;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ClassName: SolrController
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 16:03
 * @Version: 1.0
 **/
@RestController
public class SolrController {

    @Autowired
    private SolrClient solrClient;

    @RequestMapping("/solr")
    public String test() throws IOException, SolrServerException {
        SolrDocument doc = solrClient.getById("f43cd47d-fdf4-4a93-b68a-63791c67b014");
        return doc.toString();
    }

}
