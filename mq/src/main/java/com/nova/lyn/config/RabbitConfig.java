package com.nova.lyn.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 上午 11:06
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }
    @Bean
    public Queue queueobj() {
        return new Queue("hellobj");
    }


}

