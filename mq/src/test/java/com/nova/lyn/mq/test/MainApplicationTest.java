package com.nova.lyn.mq.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName MainApplication
 * @Description TODO
 * @Author Lyn
 * @Date 2019/4/11 0011 上午 11:10
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.nova"})
public class MainApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationTest.class,args);
    }
}
