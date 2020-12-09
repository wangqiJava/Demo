package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
/**
 * @author : Wangqi
 * @project_name : demo
 * @description :
 * @date : 2020-11-22 23:38
 **/
@MapperScan("com.example.demo.dao")
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringBootApplication.class, args);
    }
}
