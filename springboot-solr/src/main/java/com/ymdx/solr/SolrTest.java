package com.ymdx.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * @ClassName: SolrTest
 * @Description: TODO
 * @Author: ymdx
 * @Email: y_m_d_x@163.com
 * @Date: 2020-02-27 16:03
 * @Version: 1.0
 **/
public class SolrTest {

    public static void main(String[] args) throws IOException, SolrServerException {
        String solrUrl = "http://172.16.49.131:8983/solr/new_core";
        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl).build();
        System.out.println("============= add doc =============");
        Collection<SolrInputDocument> docs = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            SolrInputDocument doc = new SolrInputDocument();
            // 公有的字段
            doc.addField("id", UUID.randomUUID().toString());
            doc.addField("name", "solr_add_test_"+i);
            doc.addField("filename_s", "solr_test_"+i);
            docs.add(doc);
        }
        UpdateResponse rsp = client.add(docs);
        System.out.println("Add doc size" + docs.size() + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());
        UpdateResponse updateResponse = client.commit();
        System.out.println("commit doc to index" + " result:" + rsp.getStatus() + " Qtime:" + rsp.getQTime());
        System.out.println(updateResponse.toString());
    }

}
