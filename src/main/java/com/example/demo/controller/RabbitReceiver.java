package com.example.demo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author : Wangqi
 * @project_name : springboot-mydemo
 * @description :
 * @date : 2020-12-28 00:04
 **/
@Component
@RabbitListener(queues = "hello")
public class RabbitReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

}