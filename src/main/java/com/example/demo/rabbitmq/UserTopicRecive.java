package com.example.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author : Wangqi
 * @project_name : demo
 * @description :
 * @date : 2020-12-07 00:00
 **/
@Component
public class UserTopicRecive {
    @RabbitListener(queues="spring.demo")
    public void process(String user) throws InterruptedException {
        System.out.println("TopicRecive1接受的消息： "+user);
    }
}
