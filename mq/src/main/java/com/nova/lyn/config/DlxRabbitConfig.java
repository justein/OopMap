package com.nova.lyn.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DlxRabbitConfig
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/15 0015 上午 8:39
 * @Version 1.0
 */
@Configuration
public class DlxRabbitConfig {
    private static final String DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    private static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    /**死信队列*/
    @Bean("dlxQueue")
    public Queue dlxQueue() {

        Map<String, Object> args = new HashMap<>();
        args.put(DEAD_LETTER_EXCHANGE, "DLX-EXCHANGE");
        args.put(DEAD_LETTER_ROUTING_KEY, "KEY-R");

        return QueueBuilder.durable("DLX-QUEUE").withArguments(args).build();
    }

    /**交换机*/
    @Bean("dlxExchange")
    public Exchange dlxExchange() {
        return ExchangeBuilder.directExchange("DLX-EXCHANGE").durable(true).build();
    }

    /**死信转发队列*/
    @Bean("redirectQueue")
    public Queue redirectQueue() {
        return QueueBuilder.durable("REDIRECT-QUEUE").build();
    }

    /**死信队列绑定到死信路由*/
    @Bean
    public Binding deadLetterBinding() {
        return new Binding("DLX-QUEUE",Binding.DestinationType.QUEUE
                ,"DLX-EXCHANGE","KEY-DL",null);
    }

    /**转发队列绑定到死信路由*/
    @Bean
    public Binding redirectBinding() {
        return new Binding("REDIRECT-QUEUE",Binding.DestinationType.QUEUE,
                "DLX-EXCHANGE","KEY-R",null);
    }

}
