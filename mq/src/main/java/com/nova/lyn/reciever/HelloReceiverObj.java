package com.nova.lyn.reciever;

import com.nova.lyn.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloReceiver
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 上午 11:12
 * @Version 1.0
 */
@Component
@RabbitListener(queues = {"hellobj"})
public class HelloReceiverObj {

    @RabbitHandler
    public void process(User user) {
        System.out.println("Receive : "+ user);
    }
}
