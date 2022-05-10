package com.ljm.designMode.proxy.dynamic;


import com.ljm.designMode.proxy.actruely.AppleCompany;
import com.ljm.designMode.proxy.inte.PhoneCompany;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName Cilent
 * @Description
 * @Author Jim
 * @Date 2022/4/25 18:05
 **/
public class Client {
    public static void main(String[] args) {
        AppleCompany appleCompany = new AppleCompany();
        InvocationHandler invocationHandler = new Agent(appleCompany);
        PhoneCompany phoneCompanyProxy = (PhoneCompany) Proxy.newProxyInstance(
                appleCompany.getClass().getClassLoader(),
                appleCompany.getClass().getInterfaces(),
                invocationHandler);
        phoneCompanyProxy.salePhone();

    }
}
