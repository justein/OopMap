package com.nova.lyn.mq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.UUID;

/**
 * @ClassName DlxRabbitTest
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/15 0015 上午 9:08
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DlxRabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testDlxProcess() throws InterruptedException {
        String msgBody = "订单已经下单，请在15min内完成支付，否则视为主动放弃订单";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setContentEncoding("UTF-8");
            /**设置消息过期时间，此处表示订单下单多长时间后触发 短信运营商进行渠道通知*/
            messageProperties.setExpiration("10000");
            return message;

        };

        rabbitTemplate.convertAndSend("DLX-EXCHANGE","KEY-DL",msgBody,messagePostProcessor,correlationData);
        Thread.sleep(20*1000);
    }

}
