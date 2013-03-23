package com.raunak.threading;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class ThreadTest extends Thread {

    private MyObject myObject;

    public ThreadTest(MyObject myObject) {

        this.myObject = myObject;
    }

    public void run() {

        try {
            myObject.normal();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        MyObject myObject = new MyObject();
        ThreadTest t1 = new ThreadTest(myObject);
        t1.setName("1");
        ThreadTest t2 = new ThreadTest(myObject);

        t1.start();
        t2.start();

        System.out
                .println(URLEncoder
                        .encode("Mozilla/5.0 (Linux; U; Android 2.1; en-us; Nexus One Build/ERD62) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17"));
        
        
    }
}

class MyObject {

    public void staticM() throws InterruptedException {

        System.out.println("In static");
        Thread.sleep(5000);

        System.out.println("Sleeping for static");
    }

    public synchronized void normal() throws InterruptedException {

        System.out.println("In nonstatic");
        Thread.sleep(5000);

        System.out.println("Sleeping for nonstatic");
    }
}