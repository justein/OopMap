package com.nova.lyn.mq.test;

import com.nova.lyn.entity.User;
import com.nova.lyn.producer.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName com.nova.lyn.mq.test.RabbitMqTest
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 上午 11:15
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    private HelloSender helloSender;

    /**消息发送测试*/
    @Test
    public void hello() throws Exception {
        helloSender.send();
    }

    /**一对多测试*/
    @Test
    public void oneToMany() {
        for (int i = 0; i < 100; i++) {
            helloSender.send();
        }
    }

    @Test
    public void testSendUser() {
        User user = new User();
        user.setUsername("Lyn");
        user.setPassword("sdfsd");
        user.setAge(29);
        helloSender.sendUser(user);
    }
    @Test
    public void testTopic() {
//        helloSender.sendToMsgTopic();
        helloSender.sendToIntTopics();
    }
}
