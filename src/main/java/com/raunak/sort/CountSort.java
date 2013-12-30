package com.raunak.sort;

public class CountSort {

    public static void main(String[] args) {

        Integer[] input = { null, 3, 2, 4, 1, 16, 9, 10, 14, 3, 8, 7, 0, 1 };

        Integer[] x = countSort(input, 16);

        for (Integer i : x) {
            System.out.println(i);
        }
    }

    private static Integer[] countSort(Integer[] input, int maxElement) {

        Integer[] b = new Integer[input.length];

        Integer[] c = new Integer[maxElement + 1];

        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }

        for (int i = 1; i < input.length; i++) {

            c[input[i]] = c[input[i]] + 1;
        }

        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i - 1];
        }

        b[0] = null;

        for (int i = 1; i < input.length; i++) {

            b[c[input[i]]] = input[i];
            c[input[i]] = c[input[i]] - 1;
        }
        return b;
    }
}
