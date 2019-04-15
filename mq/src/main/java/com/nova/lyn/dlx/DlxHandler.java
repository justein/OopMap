package com.nova.lyn.dlx;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName DlxHandler
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/15 0015 上午 9:01
 * @Version 1.0
 */
@Component
public class DlxHandler {

//    @RabbitHandler
    @RabbitListener(queues = {"REDIRECT-QUEUE"})
    public void redirect(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println("收到死信消息，正在处理" + new String(message.getBody()));
    }
}
