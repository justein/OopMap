package com.lyn.nova.dynamicproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName CglibDynamicProxyInterceptor
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/18 0018 下午 3:38
 * @Version 1.0
 */
public class CglibDynamicProxyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method run : "+ method.getName());
        Object object = methodProxy.invokeSuper(o,objects);
        System.out.println("after method run : "+ method.getName());
        return object;
    }
}
