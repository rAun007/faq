package com.raunak.proxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class TestJavaProxy {

    public static void main(String[] args) {

        List<String> proxied = new ArrayList<String>();
        proxied.add("Raunak");

        List proxy = (List) Proxy.newProxyInstance(TestJavaProxy.class.getClassLoader(), new Class[] { List.class },
                new NoOpAddInvocationHandler(proxied));
        
        
        proxy.add("Sandeep");
        
        System.out.println(proxied);
    }
}
