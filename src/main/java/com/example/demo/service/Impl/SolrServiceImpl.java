package com.example.demo.service.Impl;

import com.example.demo.model.Products;
import com.example.demo.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: SolrServiceImpl
 * @Description: TODO
 * @Date: 2021/3/29 14:20
 * @Created: by WangQ
 */
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrClient solrClient;
    //索引库
    private static  final String CORE="core";

    @Override
    public List<Products> queryAll() {
        List<Products> products = new ArrayList<Products>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        try {
            QueryResponse queryResponse = solrClient.query(CORE,solrQuery);
            if (queryResponse != null) {
                products = queryResponse.getBeans(Products.class);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
