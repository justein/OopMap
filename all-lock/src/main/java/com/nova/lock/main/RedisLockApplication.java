package com.nova.lock.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName RedisLockApplication
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/26 0026 上午 10:24
 * @Version 1.0
 */
@SpringBootApplication
public class RedisLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class,args);
        System.out.println("Let's inspect the beans provided by Spring Boot:");
    }
}
