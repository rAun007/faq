package com.raunak.threading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureTest implements Callable<String> {

    @Override
    public String call() throws Exception {

        Thread.sleep(1000);

        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<String>> futures = new ArrayList<Future<String>>();

        Callable<String> callable = new CallableAndFutureTest();

        for (int i = 0; i < 100; i++) {

            futures.add(executorService.submit(callable));
        }

        for (Future<String> fut : futures) {
            try {
                // print the return value of Future, notice the output delay in console
                // because Future.get() waits for task to get completed
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

}
