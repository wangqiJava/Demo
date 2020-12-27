package com.example.demo;

import com.example.demo.controller.RabbitController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Wangqi
 * @project_name : springboot-mydemo
 * @description :
 * @date : 2020-12-28 00:05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private RabbitController helloSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }

}