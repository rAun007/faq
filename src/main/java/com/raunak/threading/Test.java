package com.raunak.threading;
public class Test extends Thread {

    private static Boolean isHelloPrinted = Boolean.FALSE;

    @Override
    public void run() {

        synchronized (isHelloPrinted) {
                if (this.getName().equals("hello")) {

                    if (isHelloPrinted) {
                        try {
                            isHelloPrinted.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    
                    System.out.println("Hello ");
                    isHelloPrinted = Boolean.TRUE;
                    isHelloPrinted.notify();
                }
                
                if (this.getName().equals("world")) {

                    if (!isHelloPrinted) {
                        try {
                            isHelloPrinted.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    
                    System.out.println("World");
                    isHelloPrinted = Boolean.FALSE;
                    isHelloPrinted.notify();
                }
        }
    }

    public static void main(String[] args) {

        Test test = new Test();
        test.setName("world");
        test.start();

        Test test2 = new Test();
        test2.setName("hello");
        test2.start();
    }

}