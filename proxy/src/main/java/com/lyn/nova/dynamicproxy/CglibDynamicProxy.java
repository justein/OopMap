package com.lyn.nova.dynamicproxy;

/**
 * @ClassName CglibDynamicProxy
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/18 0018 下午 3:31
 * @Version 1.0
 */
public class CglibDynamicProxy {

    public void sayHello(String str) {
        System.out.println("Hello Concrete : "+ str);
        //return "Hello Concrete : "+ str;
    }
}
