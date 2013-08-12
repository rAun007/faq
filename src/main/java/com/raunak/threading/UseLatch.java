package com.raunak.threading;

import java.util.concurrent.CountDownLatch;

public class UseLatch {

    public long calcuteRunTime(int noOfThreads) throws InterruptedException {

        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(noOfThreads);

        for (int i = 0; i < noOfThreads; i++) {
            Thread t = new Thread() {

                public void run() {

                    try {
                        
                        start.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        System.out.println("My Name is Raunak");
                    } finally {
                        end.countDown();
                    }
                };
            };
            t.start();
        }

        long startTime = System.nanoTime();
        start.countDown();

        end.await();
        
        long endTime = System.nanoTime();
        return  endTime -startTime;

    }
    
    
    public static void main(String[] args) throws InterruptedException {

        UseLatch latch = new UseLatch();
        System.out.println(latch.calcuteRunTime(5));
    }
}
