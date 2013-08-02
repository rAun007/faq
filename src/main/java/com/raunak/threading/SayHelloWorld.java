package com.raunak.threading;
class PrinterClass {

    boolean isHelloPrinted = false;

    synchronized void printWorld() throws InterruptedException {

        if (!isHelloPrinted)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("world");
        Thread.sleep(1000);
        isHelloPrinted = false;
        notify();
    }

    synchronized void printHello() {

        if (isHelloPrinted)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        isHelloPrinted = true;
        System.out.print("Hello ");
        notify();
        
    }
}

class Hello implements Runnable {
    PrinterClass printer;

    Hello(PrinterClass printerClass) {

        this.printer = printerClass;
        new Thread(this, "Hello").start();
    }

    public void run() {

        while (true) {
            printer.printHello();
        }
    }
}

class World implements Runnable {
    PrinterClass printerClass;

    World(PrinterClass printerClass) {

        this.printerClass = printerClass;
        new Thread(this, "World").start();
    }

    public void run() {

        while (true) {
            try {
                printerClass.printWorld();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

public class SayHelloWorld {

    public static void main(String args[]) {

        PrinterClass printerClass = new PrinterClass();
        new World(printerClass);
        new Hello(printerClass);
    }
}