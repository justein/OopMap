package com.nova.lyn.reciever;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName FanoutReceiverA
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/12 0012 上午 10:52
 * @Version 1.0
 */
@Component
@RabbitListener(queues = {"fanout.b"})
public class FanoutReceiverB {
    @RabbitHandler
    public void process(String message) {
        System.out.println("fanout Receiver B  : " + message);
    }
}
