package com.nova.lock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName RedisLockController
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/26 0026 上午 10:22
 * @Version 1.0
 */
@Controller
@RequestMapping("api/redislock")
public class RedisLockController {

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @RequestMapping("redis")
    public void testReentry() throws InterruptedException {

        Lock lock = redisLockRegistry.obtain("lock");
        boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
        System.out.println("b1 is :" + b1);

        TimeUnit.SECONDS.sleep(5);

        boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
        System.out.println("b2 is :" + b2);

        lock.unlock();
        lock.unlock();
    }
}
