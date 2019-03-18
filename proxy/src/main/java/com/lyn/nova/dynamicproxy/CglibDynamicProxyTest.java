package com.lyn.nova.dynamicproxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @ClassName CglibDynamicProxyTest
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/18 0018 下午 3:40
 * @Version 1.0
 */
public class CglibDynamicProxyTest {

    public static void main(String[] args) {
        //设置cglib生成的代理类输出位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\cglibgenerateclass");

        /**增强*/
        Enhancer enhancer = new Enhancer();
        /**继承被代理类，这里正体现了cglib是基于继承实现的代理*/
        enhancer.setSuperclass(CglibDynamicProxy.class);
        /**设置回调*/
        enhancer.setCallback(new CglibDynamicProxyInterceptor());
        /***/
        CglibDynamicProxy cglibDynamicProxy = (CglibDynamicProxy) enhancer.create();
        cglibDynamicProxy.sayHello("Lyn神");
    }

}
