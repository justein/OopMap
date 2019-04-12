package com.nova.lyn.producer;

import com.nova.lyn.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName HelloSender
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 上午 11:08
 * @Version 1.0
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {

        String msg = "hello "+ LocalDateTime.now();
        System.out.println("Sender : "+ msg);
        this.amqpTemplate.convertAndSend("hello",msg);
    }

    public void sendUser(User user) {
        this.amqpTemplate.convertAndSend("hellobj", user);
    }

    public void sendToMsgTopic() {
        String msg = "this is msg content";
        System.out.println("Sender1 : "+ msg);
        this.amqpTemplate.convertAndSend("exchange", "topic.msg", msg);
    }

    public void sendToIntTopics() {
        String msg = "this is 123 content";
        System.out.println("Sender2 : "+ msg);
        this.amqpTemplate.convertAndSend("exchange", "topic.int", msg);
    }

    public void sentToFanoutExchange() {
        String msg = "这是发往fanout的测试消息";
        System.out.println("Sender fan : "+msg);
        this.amqpTemplate.convertAndSend("fanoutExchange","",msg);
    }
}
