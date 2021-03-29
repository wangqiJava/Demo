package com.example.demo.model;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * @Classname: Products
 * @Description: solr实体映射类
 * @Date: 2021/3/26 16:13
 * @Created: by WangQ
 */
public class Products implements Serializable {
    @Field("pid")
    private String pid;
    @Field("product_name")
    private String pname;
    @Field("product_catalog_name")
    private String catalogName;
    @Field("product_price")
    private Double price;
    @Field("product_description")
    private String description;
    @Field("product_picture")
    private String picture;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
