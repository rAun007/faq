package com.raunak.algo;

/**
 * Created by raunak.agrawal on 10/18/16.
 */
public class Binary {

    public static void main(String[] args) {

        int number = 15;


        for (int i = 1 << 8; i > 0; i = i/2) {
            if ((number & i) > 0) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }

        System.out.println("Method 2");
        bin(4);
    }

    public static void bin(int n) {
        /* step 1 */
        if (n > 1) bin(n / 2);

        /* step 2 */
        System.out.println( n % 2);
    }
}
