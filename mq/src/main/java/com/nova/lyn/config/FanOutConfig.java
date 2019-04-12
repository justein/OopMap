package com.nova.lyn.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanOutConfig
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/12 0012 上午 10:42
 * @Version 1.0
 */
@Configuration
public class FanOutConfig {

    @Bean
    public Queue faoutA() {
        return new Queue("fanout.a");
    }
    @Bean
    public Queue faoutB() {
        return new Queue("fanout.b");
    }
    @Bean
    public Queue faoutC() {
        return new Queue("fanout.c");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue faoutA,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(faoutA).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue faoutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(faoutB).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue faoutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(faoutC).to(fanoutExchange);
    }

}

