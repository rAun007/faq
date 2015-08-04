package com.java.eight.tutorial.lambda;

/**
 * Created by raunak.agrawal on 8/1/15.
 */
public class CapturingLambda {

    public static void main(String[] args) {

        /**
         * Lambdas are allowed to capture (that is, to reference in their bodies) instance variables and static
         * variables without restrictions. But local variables have to be explicitly declared final or are effectively
         * final. In other words, lambda expressions can capture local variables that are assigned to them only once.
         * (Note: capturing an instance variable can be seen as capturing the final local variable this.)
         */
        int pNo = 8080; // The
        Runnable r = () -> System.out.println(pNo );
        Thread t = new Thread(r);
        t.start();
        //pNo = 6; //Compilation error
        System.out.println("Done");
    }
}
