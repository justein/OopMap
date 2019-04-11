package com.nova.lyn.reciever;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName TopicQueueReceiver
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 下午 3:33
 * @Version 1.0
 */
@Component
@RabbitListener(queues = {"topic.int"})
public class TopicQueueReceiverAll {

    @RabbitHandler
    public void handleMsg(String msg) {
        System.out.println(" # Receiver : "+ msg);
    }

}
