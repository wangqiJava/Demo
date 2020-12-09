package com.example.demo;

import com.example.demo.message.Receiver;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import java.util.concurrent.CountDownLatch;

/**
*@Description: 测试Redis消息队列
 * 在spring data redis中，利用redis发送一条消息和接受一条消息，需要三样东西：
 * 一个连接工厂
 * 一个消息监听容器
 * Redis template
*@Param: 
*@Author: Wangqi
*@Date: 2020/11/22
*@Time: 23:45
*@Version:1.0
*/
@MapperScan("com.example.demo.dao")
@SpringBootApplication
public class SpringbootRedisApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootRedisApplication.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringbootRedisApplication.class, args);

        try {
            StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
            CountDownLatch latch = ctx.getBean(CountDownLatch.class);

            LOGGER.info("Sending message...");
            template.convertAndSend("chat", "Hello from Redis!");
            latch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

}
