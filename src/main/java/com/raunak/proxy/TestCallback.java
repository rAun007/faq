package com.raunak.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TestCallback implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        if ("hashCode".equals(method.getName())) {

            return 0;
        }
        return proxy.invokeSuper(obj, args);
    }

}
