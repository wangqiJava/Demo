package com.example.demo.controller;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@EnableApolloConfig//apollo连接配置中心
@RestController
public class textapollo {
    @Value("${username}")
    private String name;

    @Autowired
    private Person person;

    @RequestMapping("/helloApollo")
    public String get() {

        return "hello"+name+person.toString();
    }
}

