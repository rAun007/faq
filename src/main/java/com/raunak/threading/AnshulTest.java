package com.raunak.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AnshulTest {

    public static String getMetadata() throws InterruptedException {
        
        Thread.sleep(5000);
        return "some metadata";
    }

    static class ThreadImpl implements Callable<String> {

        @Override
        public String call() throws Exception {

            return getMetadata();
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executors = Executors.newFixedThreadPool(10);

        List<Future<String>> futures = new ArrayList<Future<String>>();

        for (int i = 0; i < 100; i++) {
            System.out.println("adding");
            futures.add(executors.submit(new ThreadImpl()));
        }

        for (int j = 0; j < futures.size(); j++) {
            
            try {
                System.out.println(futures.get(j).get(2000, TimeUnit.MILLISECONDS));
            } catch (TimeoutException e) {
                System.out.println("Going to cancel task");
                futures.get(j).cancel(false);
            }
        }

    }
}
