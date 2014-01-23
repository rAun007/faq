package com.raunak.proxy;

import net.sf.cglib.proxy.Enhancer;

public class TestCGLibProxy {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Object.class);
        enhancer.setCallback(new TestCallback());

        Object o = enhancer.create();
        System.out.println(o.hashCode());
    }
}
