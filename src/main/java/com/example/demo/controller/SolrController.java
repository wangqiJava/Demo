package com.example.demo.controller;

import com.example.demo.model.Products;
import com.example.demo.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Classname: SolrController
 * @Description: TODO
 * @Date: 2021/3/29 14:32
 * @Created: by WangQ
 */
@RestController
@RequestMapping("solr/")
@CrossOrigin
public class SolrController {

    @Autowired
    private SolrService solrService;

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("queryAll")
    public List<Products> query() {
        return solrService.queryAll();
    }

}
