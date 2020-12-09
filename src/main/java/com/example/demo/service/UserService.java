package com.example.demo.service;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.UUID;


/**
 * @author : Wangqi
 * @project_name : demo
 * @description :  生产者推送消息
 * @date : 2020-12-06 23:54
 **/
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RabbitTemplate template;

    /**
     * 增加用户
     *
     */
    public boolean addPerson(User user) throws Exception {
        Assert.notNull(user, "添加对象信息不能为空");

        Assert.hasText(user.getUserId(), "添加对象信息用户编号不能为空");
        Assert.notNull(user.getAge(), "添加对象信息年龄不能为空");

        template.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY, user.toString());
//        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                if (!ack) {
//                    logger.info("send message failed: " + cause); //+ correlationData.toString());
//                    throw new RuntimeException("send error " + cause);
//                } else {
//                    logger.info("send to broke ok" + correlationData.getId());
//                }
//            }
//        });

        return true;
    }

    private Message buildMessage(User user) throws Exception {
        Message message = MessageBuilder.withBody(user.toString().getBytes())
                .setMessageId(UUID.randomUUID().toString()).setContentType("application/json").build();
        return message;
    }
}
