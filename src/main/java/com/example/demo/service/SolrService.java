package com.example.demo.service;

import com.example.demo.model.Products;

import java.util.List;

/**
 * @Classname: SolrService
 * @Description: TODO
 * @Date: 2021/3/29 14:20
 * @Created: by WangQ
 */
public interface SolrService {
    List<Products> queryAll();
}
