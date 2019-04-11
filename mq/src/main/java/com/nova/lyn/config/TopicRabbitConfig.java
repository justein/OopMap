package com.nova.lyn.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TopicRabbitConfig
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 下午 3:14
 * @Version 1.0
 */
@Configuration
public class TopicRabbitConfig {

    final static String msgTopic = "topic.msg";
    final static String intTopic = "topic.int";

    @Bean
    public Queue msgQueue() {
        return new Queue(TopicRabbitConfig.msgTopic);
    }

    @Bean
    public Queue intQueue() {
        return new Queue(TopicRabbitConfig.intTopic);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    @Bean
    Binding bindingExchangeMsgQueue(Queue msgQueue, TopicExchange exchange) {
        return BindingBuilder.bind(msgQueue).to(exchange).with("topic.msg");
    }
    @Bean
    Binding bindingExchangeIntQueue(Queue intQueue, TopicExchange exchange) {
        return BindingBuilder.bind(intQueue).to(exchange).with("topic.#");
    }

}
