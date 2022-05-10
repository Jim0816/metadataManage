package com.ljm.designMode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName Agent
 * @Description
 * @Author Jim
 * @Date 2022/4/25 17:57
 **/
public class Agent implements InvocationHandler {

    // 被代理的真实对象
    private Object target;

    public Agent(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeSale();
        Object result = method.invoke(target, args);
        afterSale();
        return result;
    }

    private void beforeSale(){
        System.out.println("代购售前服务......");
    }

    private void afterSale(){
        System.out.println("代购售后服务......");
    }
}
