package com.raunak.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * This class shows how we can use Callable with thread api (which doesn't support call() method)
 * 
 * @author raunak.agrawal
 * 
 */
public class FutureTaskTest2 implements Callable<String> {

    @Override
    public String call() throws Exception {

        Thread.sleep(1000);
        return "raunak";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTaskTest2 t = new FutureTaskTest2();
        FutureTask<String> ft = new FutureTask<String>(t);
        Thread th = new Thread(ft);

        th.start();

        System.out.println(ft.get());
    }

}
