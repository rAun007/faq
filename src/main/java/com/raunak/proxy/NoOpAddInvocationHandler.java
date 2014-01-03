package com.raunak.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class NoOpAddInvocationHandler implements InvocationHandler {

    private final List proxied;

    public NoOpAddInvocationHandler(List proxied) {

        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().startsWith("add")) {

            return false;
        }
        return method.invoke(proxied, args);
    }

}
