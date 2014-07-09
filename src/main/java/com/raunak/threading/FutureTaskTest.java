/**
 * 
 */
package com.raunak.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * What if we want to override some of the methods of Future interface, for example overriding get() method to timeout after some default
 * time rather than waiting indefinitely, in this case FutureTask class comes handy that is the base implementation of Future interface.
 * 
 * @author raunak.agrawal
 * 
 */
public class FutureTaskTest implements Callable<String> {

    private long waitTime;

    public FutureTaskTest(int timeInMillis) {

        this.waitTime = timeInMillis;
    }

    @Override
    public String call() throws Exception {

        Thread.sleep(waitTime);
        // return the thread name executing this callable task
        return Thread.currentThread().getName();
    }

    static class FutureTaskRunner {

        public static void main(String[] args) {

            FutureTaskTest f1 = new FutureTaskTest(1000);
            FutureTaskTest f2 = new FutureTaskTest(2000);

            FutureTask<String> ft1 = new FutureTask<String>(f1);
            FutureTask<String> ft2 = new FutureTask<String>(f2);

            ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.submit(ft1);
            executor.submit(ft2);

            while (true) {
                try {
                    if (ft1.isDone() && ft2.isDone()) {
                        System.out.println("Done");
                        // shut down executor service
                        executor.shutdown();
                        return;
                    }

                    if (!ft1.isDone()) {
                        // wait indefinitely for future task to complete
                        System.out.println("FutureTask1 output=" + ft1.get());
                    }

                    System.out.println("Waiting for FutureTask2 to complete");
                    String s = ft2.get(200L, TimeUnit.MILLISECONDS);
                    if (s != null) {
                        System.out.println("FutureTask2 output=" + s);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    //Do Nothing
                }
            }
        }
    }
}
